package com.josue.challenge_eldar.domain.model;

import java.time.OffsetDateTime;
import java.util.List;

public record Party(
    String id,
    String name,
    OffsetDateTime date,
    PartyStatus status,
    List<Guest> guests
) {

}
