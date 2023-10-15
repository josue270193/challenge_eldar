package com.josue.challenge_eldar.infrastructure.outbound.mongo.entity;

import java.time.LocalDate;

public record GuestEntity(
    String id,
    String name,
    String lastName,
    LocalDate birthday,
    String nationalIdentification
) {

}
