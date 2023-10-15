package com.josue.challenge_eldar.application.mapper;

import com.josue.challenge_eldar.application.response.GuestResponse;
import com.josue.challenge_eldar.application.response.PartyCreateResponse;
import com.josue.challenge_eldar.application.response.PartyEditResponse;
import com.josue.challenge_eldar.application.response.PartyGetResponse;
import com.josue.challenge_eldar.application.response.PartySendInvitationResponse;
import com.josue.challenge_eldar.domain.model.Party;
import org.springframework.stereotype.Component;

@Component
public class PartyMapper {

  public PartyCreateResponse fromEntityToCreateResponse(Party party) {
    var guests = party.guests().stream()
        .map(guest -> new GuestResponse(guest.id(),
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()
        ))
        .toList();
    return new PartyCreateResponse(
        party.id(),
        party.name(),
        party.date(),
        party.status(),
        guests
    );
  }

  public PartyEditResponse fromEntityToEditResponse(Party party) {
    var guests = party.guests().stream()
        .map(guest -> new GuestResponse(guest.id(),
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()
        ))
        .toList();
    return new PartyEditResponse(
        party.id(),
        party.name(),
        party.date(),
        party.status(),
        guests
    );
  }

  public PartyGetResponse fromEntityToGetResponse(Party party) {
    var guests = party.guests().stream()
        .map(guest -> new GuestResponse(guest.id(),
            guest.name(),
            guest.lastName(),
            guest.birthday(),
            guest.nationalIdentification()
        ))
        .toList();
    return new PartyGetResponse(
        party.id(),
        party.name(),
        party.date(),
        party.status(),
        guests
    );
  }

}
