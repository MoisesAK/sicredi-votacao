package com.prv.votacao.repository;

import com.prv.votacao.models.Agenda;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AgendaRepository extends ReactiveMongoRepository<Agenda, Long> {}
