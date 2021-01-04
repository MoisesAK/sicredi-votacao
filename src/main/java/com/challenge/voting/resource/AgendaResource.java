package com.challenge.voting.resource;

import com.challenge.voting.exception.NotFoundException;
import com.challenge.voting.model.Agenda;
import com.challenge.voting.service.AgendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("agendas")
public class AgendaResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteResource.class);

    private final AgendaService service;

    public AgendaResource(AgendaService service) {
        this.service = service;
    }

    @GetMapping("{agendaId}")
    public Mono<Agenda> findById(@PathVariable(value = "agendaId") String id){
        LOGGER.info("getAgendaById: {}", id);
        return service.findById(id).switchIfEmpty(Mono.error(new NotFoundException("pauta nao encontrada")));
    }

    @GetMapping
    public Flux<Agenda> findAll(){
        LOGGER.info("getAllAgendas:");
        return service.findAll();
    }

    @PostMapping
    public Mono<Agenda> save(@RequestBody Agenda agenda) {
        LOGGER.info("agenda: {}", agenda);
        return service.save(agenda);
    }
}
