package com.prv.votacao.service;

import com.prv.votacao.models.Agenda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AgendaService {

    Mono<Agenda> findById(long id);

    Flux<Agenda> findAll();

    Mono<Agenda> save(Agenda agenda);

}
