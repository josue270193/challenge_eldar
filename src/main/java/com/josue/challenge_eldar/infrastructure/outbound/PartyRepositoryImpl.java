package com.josue.challenge_eldar.infrastructure.outbound;

import com.josue.challenge_eldar.domain.exception.PartyCannotEditException;
import com.josue.challenge_eldar.domain.exception.PartyNotFoundException;
import com.josue.challenge_eldar.domain.model.Party;
import com.josue.challenge_eldar.domain.model.PartyStatus;
import com.josue.challenge_eldar.domain.repository.PartyRepository;
import com.josue.challenge_eldar.infrastructure.outbound.mongo.PartyMongoRepository;
import com.josue.challenge_eldar.infrastructure.outbound.mongo.mapper.PartyEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class PartyRepositoryImpl implements PartyRepository {

  private final PartyMongoRepository partyMongoRepository;
  private final PartyEntityMapper partyEntityMapper;

  @Override
  public Mono<Party> createParty(Party partyDto) {
    return Mono.just(partyDto)
        .map(partyEntityMapper::fromDtoToCreateEntity)
        .flatMap(partyMongoRepository::save)
        .map(partyEntityMapper::fromEntityToDto);
  }

  @Override
  public Mono<Party> editParty(Party partyDto) {
    return getParty(partyDto.id())
        .flatMap(partyFound -> {
          if (partyFound.status() == null || partyFound.status() != PartyStatus.CREATED) {
            return Mono.error(new PartyCannotEditException());
          }
          return Mono.just(partyEntityMapper.copyStatus(partyFound, partyDto));
        })
        .flatMap(partyMongoRepository::save)
        .map(partyEntityMapper::fromEntityToDto);
  }

  @Override
  public Mono<Party> cancelParty(String idParty) {
    return getParty(idParty)
        .map(partyEntityMapper::fromDtoToCancelEntity)
        .flatMap(partyMongoRepository::save)
        .map(partyEntityMapper::fromEntityToDto);
  }

  @Override
  public Mono<Party> sendInvitationParty(String idParty) {
    return getParty(idParty)
        .map(partyEntityMapper::fromDtoToSendInvitationEntity)
        .flatMap(partyMongoRepository::save)
        .map(partyEntityMapper::fromEntityToDto);
  }

  @Override
  public Mono<Party> getParty(String idParty) {
    return Mono.just(idParty)
        .flatMap(partyMongoRepository::findById)
        .map(partyEntityMapper::fromEntityToDto)
        .switchIfEmpty(Mono.error(new PartyNotFoundException()));
  }

  @Override
  public Flux<Party> getAllParty() {
    return partyMongoRepository.findAll()
        .map(partyEntityMapper::fromEntityToDto);
  }
}
