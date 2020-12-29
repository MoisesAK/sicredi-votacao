package com.challenge.voting.service;

import com.challenge.voting.repository.AgendaRepository;
import com.challenge.voting.models.Agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AgendaService {

    @Autowired
    AgendaRepository repository;

    public Mono<Agenda> findById(String id) {
        return repository.findById(id);
    }

    public Flux<Agenda> findAll() {
        return repository.findAll();
    }

    public Mono<Agenda> save(Agenda agenda) {
        return repository.save(agenda);
    }

}
