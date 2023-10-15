package com.josue.challenge_eldar.application.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record GuestResponse(
    String id,
    String name,
    String lastName,
    LocalDate birthday,
    String nationalIdentification
) {

}
