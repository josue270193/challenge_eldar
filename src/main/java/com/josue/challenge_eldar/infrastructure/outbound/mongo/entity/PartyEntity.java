package com.josue.challenge_eldar.infrastructure.outbound.mongo.entity;

import com.josue.challenge_eldar.domain.model.PartyStatus;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("parties")
public record PartyEntity(

    @Id
    String id,
    String name,
    Date date,
    PartyStatus status,
    List<GuestEntity> guests

) {

}
