package com.josue.challenge_eldar.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PartyCannotEditException extends RuntimeException {

  public PartyCannotEditException() {
    super("Party can not be edited");
  }

}
