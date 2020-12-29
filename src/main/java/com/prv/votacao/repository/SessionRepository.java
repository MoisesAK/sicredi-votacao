package com.prv.votacao.repository;

import com.prv.votacao.models.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SessionRepository extends ReactiveMongoRepository<Session, Long> {}
