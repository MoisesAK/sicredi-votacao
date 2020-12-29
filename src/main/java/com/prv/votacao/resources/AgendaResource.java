package com.prv.votacao.resources;

import com.prv.votacao.models.Agenda;
import com.prv.votacao.models.Session;
import com.prv.votacao.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("pauta")
public class AgendaResource {

    @Autowired
    AgendaService service;

    @GetMapping("{id}")
    public Mono<Agenda> findById(@PathVariable(value = "id") long id){
        return service.findById(id);
    }

    @GetMapping
    public Flux<Agenda> findAll(){
        return service.findAll();
    }

    @PostMapping
    public Mono<Agenda> save(@RequestBody Agenda agenda){
        return service.save(agenda);
    }

}
