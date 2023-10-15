package com.josue.challenge_eldar.application.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record GuestEditRequest(
    String id,
    @NotEmpty
    String name,
    @NotEmpty
    String lastName,
    @NotNull
    LocalDate birthday,
    @NotEmpty
    String nationalIdentification
) {

}
