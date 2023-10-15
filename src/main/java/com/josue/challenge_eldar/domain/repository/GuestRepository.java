package com.josue.challenge_eldar.domain.repository;

import com.josue.challenge_eldar.domain.model.Guest;
import reactor.core.publisher.Mono;

public interface GuestRepository {

  Mono<Guest> getGuestById(String id);

  Mono<Guest> saveGuest(Guest guest);

}
