package com.josue.challenge_eldar.domain.repository;

import com.josue.challenge_eldar.domain.model.Party;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PartyRepository {

  Mono<Party> createParty(Party party);

  Mono<Party> editParty(Party party);

  Mono<Party> cancelParty(String idParty);

  Mono<Party> sendInvitationParty(String idParty);

  Mono<Party> getParty(String idParty);

  Flux<Party> getAllParty();

}
