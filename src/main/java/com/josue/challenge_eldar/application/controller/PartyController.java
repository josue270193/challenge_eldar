package com.josue.challenge_eldar.application.controller;

import com.josue.challenge_eldar.application.request.PartyCreateRequest;
import com.josue.challenge_eldar.application.request.PartyEditRequest;
import com.josue.challenge_eldar.application.request.PartySendInvitationRequest;
import com.josue.challenge_eldar.application.response.PartyCreateResponse;
import com.josue.challenge_eldar.application.response.PartyEditResponse;
import com.josue.challenge_eldar.application.response.PartyGetResponse;
import com.josue.challenge_eldar.application.response.PartySendInvitationResponse;
import com.josue.challenge_eldar.application.service.PartyService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/party")
@AllArgsConstructor
@CrossOrigin("*")
public class PartyController {

  private final PartyService partyService;

  @PostMapping("/create")
  Mono<PartyCreateResponse> createParty(
      @Valid @RequestBody PartyCreateRequest request
  ) {
    return Mono.just(request)
        .flatMap(partyService::createParty);
  }

  @PostMapping("/edit")
  Mono<PartyEditResponse> editParty(
      @Valid @RequestBody PartyEditRequest request
  ) {
    return Mono.just(request)
        .flatMap(partyService::editParty);
  }

  @GetMapping("/get")
  Mono<PartyGetResponse> getParty(
      @RequestParam String id
  ) {
    return Mono.just(id)
        .flatMap(partyService::getParty);
  }

  @GetMapping("/getAll")
  Mono<List<PartyGetResponse>> getAllParty() {
    return partyService.getAllParty()
        .collectList();
  }

  @PostMapping("/sendInvitation")
  Mono<PartySendInvitationResponse> sendInvitationParty(
      @Valid @RequestBody PartySendInvitationRequest request
  ) {
    return Mono.just(request)
        .flatMap(partyService::sendInvitationParty);
  }

}
