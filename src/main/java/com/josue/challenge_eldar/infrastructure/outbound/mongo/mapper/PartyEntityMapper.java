package com.josue.challenge_eldar.infrastructure.outbound.mongo.mapper;

import com.josue.challenge_eldar.domain.model.Guest;
import com.josue.challenge_eldar.domain.model.Party;
import com.josue.challenge_eldar.domain.model.PartyStatus;
import com.josue.challenge_eldar.infrastructure.outbound.mongo.entity.GuestEntity;
import com.josue.challenge_eldar.infrastructure.outbound.mongo.entity.PartyEntity;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PartyEntityMapper {

  public PartyEntity fromDtoToCreateEntity(Party dto) {
    var guestEntityList = dto.guests().stream()
        .map(guest -> new GuestEntity(
            UUID.randomUUID().toString(),
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()
        ))
        .toList();
    return new PartyEntity(
        UUID.randomUUID().toString(),
        dto.name(),
        Date.from(dto.date().toInstant()),
        PartyStatus.CREATED,
        guestEntityList
    );
  }

  public PartyEntity fromDtoToEntity(Party dto) {
    return getPartyEntity(dto, dto, null);
  }

  public Party fromEntityToDto(PartyEntity entity) {
    var guestDtoList = entity.guests().stream()
        .map(guest -> new Guest(
            guest.id(),
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()
        ))
        .toList();
    return new Party(
        entity.id(),
        entity.name(),
        OffsetDateTime.ofInstant(entity.date().toInstant(), ZoneOffset.UTC),
        entity.status(),
        guestDtoList
    );
  }

  public PartyEntity copyStatus(Party from, Party to) {
    return getPartyEntity(from, to, null);
  }

  public PartyEntity fromDtoToSendInvitationEntity(Party party) {
    return getPartyEntity(party, party, PartyStatus.COMPLETED);
  }

  public PartyEntity fromDtoToCancelEntity(Party party) {
    return getPartyEntity(party, party, PartyStatus.CANCEL);
  }

  private PartyEntity getPartyEntity(Party from, Party to, PartyStatus status) {
    var guestEntityList = to.guests().stream()
        .map(guest -> new GuestEntity(
            StringUtils.hasText(guest.id()) ? guest.id() : UUID.randomUUID().toString(),
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()
        ))
        .toList();
    return new PartyEntity(to.id(),
        to.name(),
        Date.from(to.date().toInstant()),
        status != null ? status : from.status(),
        guestEntityList);
  }

}
