package com.josue.challenge_eldar.application.service;

import com.josue.challenge_eldar.application.mapper.PartyMapper;
import com.josue.challenge_eldar.application.request.PartyCreateRequest;
import com.josue.challenge_eldar.application.request.PartyEditRequest;
import com.josue.challenge_eldar.application.request.PartySendInvitationRequest;
import com.josue.challenge_eldar.application.response.PartyCreateResponse;
import com.josue.challenge_eldar.application.response.PartyEditResponse;
import com.josue.challenge_eldar.application.response.PartyGetResponse;
import com.josue.challenge_eldar.application.response.PartySendInvitationResponse;
import com.josue.challenge_eldar.domain.model.Guest;
import com.josue.challenge_eldar.domain.model.Party;
import com.josue.challenge_eldar.domain.model.PartyStatus;
import com.josue.challenge_eldar.domain.repository.PartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PartyService {

  private final PartyRepository partyRepository;
  private final PartyMapper partyMapper;

  public Mono<PartyCreateResponse> createParty(PartyCreateRequest request) {

    var guestDtoList = request.guests().stream()
        .map(guest -> new Guest(null,
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()))
        .toList();
    var partyDto = new Party(null,
        request.name(),
        request.date(),
        PartyStatus.CREATED,
        guestDtoList);

    return partyRepository.createParty(partyDto)
        .map(partyMapper::fromEntityToCreateResponse);
  }

  public Mono<PartyEditResponse> editParty(PartyEditRequest request) {
    var guestDtoList = request.guests().stream()
        .map(guest -> new Guest(guest.id(),
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()))
        .toList();
    var partyDto = new Party(request.id(),
        request.name(),
        request.date(),
        null,
        guestDtoList);

    return partyRepository.editParty(partyDto)
        .map(partyMapper::fromEntityToEditResponse);
  }

  public Mono<PartyGetResponse> getParty(String idParty) {
    return partyRepository.getParty(idParty)
        .map(partyMapper::fromEntityToGetResponse);
  }

  public Mono<PartySendInvitationResponse> sendInvitationParty(PartySendInvitationRequest request) {
    return partyRepository.sendInvitationParty(request.id())
        .map(party -> new PartySendInvitationResponse(party.status() == PartyStatus.COMPLETED));
  }

  public Flux<PartyGetResponse> getAllParty() {
    return partyRepository.getAllParty()
        .map(partyMapper::fromEntityToGetResponse);
  }
}
