package com.josue.challenge_eldar.application.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

public record PartyEditRequest(
    @NotEmpty
    String id,
    @NotEmpty
    String name,
    @NotNull
    OffsetDateTime date,
    @Valid
    @NotNull
    List<GuestEditRequest> guests
) {

}
