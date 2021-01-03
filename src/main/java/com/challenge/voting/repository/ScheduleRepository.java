package com.challenge.voting.repository;

import com.challenge.voting.model.Agenda;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ScheduleRepository extends ReactiveMongoRepository<Agenda, String> {}
