package com.josue.challenge_eldar.application.response;

import com.josue.challenge_eldar.domain.model.PartyStatus;
import java.time.OffsetDateTime;
import java.util.List;

public record PartyCreateResponse(
    String id,
    String name,
    OffsetDateTime date,
    PartyStatus status,
    List<GuestResponse> guests
) {

}
