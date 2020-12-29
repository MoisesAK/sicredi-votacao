package com.challenge.voting.repository;

import com.challenge.voting.models.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SessionRepository extends ReactiveMongoRepository<Session, String> {}
