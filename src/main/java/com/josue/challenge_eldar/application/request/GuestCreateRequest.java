package com.josue.challenge_eldar.application.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record GuestCreateRequest(
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
