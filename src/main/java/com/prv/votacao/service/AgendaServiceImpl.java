package com.prv.votacao.service;

import com.prv.votacao.models.Agenda;
import com.prv.votacao.models.Session;
import com.prv.votacao.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AgendaServiceImpl implements AgendaService {

    @Autowired
    AgendaRepository repository;

    @Override
    public Mono<Agenda> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Agenda> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Agenda> save(Agenda agenda) {
        return repository.save(agenda);
    }

}