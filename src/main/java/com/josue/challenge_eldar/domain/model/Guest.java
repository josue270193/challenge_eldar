package com.josue.challenge_eldar.domain.model;

import java.time.LocalDate;

public record Guest(
    String id,
    String name,
    String lastName,
    LocalDate birthday,
    String nationalIdentification
) {

}
