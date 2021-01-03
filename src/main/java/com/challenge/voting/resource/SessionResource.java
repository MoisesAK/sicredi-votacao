package com.challenge.voting.resource;

import com.challenge.voting.model.Session;
import com.challenge.voting.service.SessionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class SessionResource {

    private final SessionService service;


    public SessionResource(SessionService service) {
        this.service = service;
    }

    @GetMapping("sessions/{sessaoId}")
    public Mono<Session> findByid(@PathVariable("sessaoId") String id) {
        return service.findById(id);
    }

    @GetMapping("sessions")
    public Flux<Session> findAll() {
        return service.findAll();
    }

    /*
        Conhecendo a pauta (identificador da pauta) é possível abrir uma sessão para a pauta.
     */
    @PostMapping("pautas/{pautaId}/sessoes")
    public Mono<Session> save(@PathVariable("pautaId") String pautaId, @RequestBody Session session) {
        return service.save(pautaId, session);
    }
}
