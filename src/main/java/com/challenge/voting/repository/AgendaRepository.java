package com.challenge.voting.repository;

import com.challenge.voting.model.Agenda;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AgendaRepository extends ReactiveMongoRepository<Agenda, String> {}
