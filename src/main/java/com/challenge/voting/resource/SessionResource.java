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

    /*
        Conhecendo a pauta (identificador da pauta) é possível abrir uma sessão para a pauta.
     */
    @PostMapping("pautas/{pautaId}/sessions")
    public Mono<Session> save(@PathVariable("pautaId") String pautaId, @RequestBody Session session) {
        return service.save(pautaId, session);
    }
}
