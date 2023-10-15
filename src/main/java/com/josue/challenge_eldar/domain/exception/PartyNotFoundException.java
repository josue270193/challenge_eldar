package com.josue.challenge_eldar.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PartyNotFoundException extends RuntimeException {

  public PartyNotFoundException() {
    super("Party not found");
  }

}
