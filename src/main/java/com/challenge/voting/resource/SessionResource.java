package com.challenge.voting.resource;

import com.challenge.voting.model.Agenda;
import com.challenge.voting.model.Session;
import com.challenge.voting.service.AgendaService;
import com.challenge.voting.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class SessionResource {

    private final SessionService service;

    private final AgendaService agendaService;

    public SessionResource(SessionService service, AgendaService agendaService) {
        this.service = service;
        this.agendaService = agendaService;
    }

    @GetMapping("sessions/{sessionId}")
    public Mono<Session> findByid(@PathVariable("sessionId") String id){
        return service.findById(id);
    }

    @GetMapping("sessions")
    public Flux<Session> findAll() {
        return service.findAll();
    }

    @PostMapping("agendas/{agendaId}/sessions")
    public Mono<ResponseEntity<Session>> save(@PathVariable("agendaId") String id, @RequestBody Session session){
        return agendaService.findById(id)
                .map(Agenda::getId)
                .map(session::withAgendaId)
                .flatMap(service::save)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
