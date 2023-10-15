package com.josue.challenge_eldar.infrastructure.outbound.mongo;

import com.josue.challenge_eldar.infrastructure.outbound.mongo.entity.PartyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyMongoRepository extends ReactiveCrudRepository<PartyEntity, String> {

}
