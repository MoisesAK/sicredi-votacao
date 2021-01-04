package com.challenge.voting.resource;

import com.challenge.voting.exception.NotFoundException;
import com.challenge.voting.model.Agenda;
import com.challenge.voting.service.AgendaService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("agendas")
public class AgendaResource {

    private final AgendaService service;

    public AgendaResource(AgendaService service) {
        this.service = service;
    }

    @GetMapping("{agendaId}")
    public Mono<Agenda> findById(@PathVariable(value = "agendaId") String id){
        return service.findById(id).switchIfEmpty(Mono.error(new NotFoundException("pauta nao encontrada")));
    }

    @GetMapping
    public Flux<Agenda> findAll(){
        return service.findAll();
    }

    /*
        Esse é o início do fluxo. Aqui iniciamos uma pauta.
    */
    @PostMapping
    public Mono<Agenda> save(@RequestBody Agenda agenda) {
        return service.save(agenda);
    }
}
