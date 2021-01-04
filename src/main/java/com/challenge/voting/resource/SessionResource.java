package com.challenge.voting.resource;

import com.challenge.voting.model.Session;
import com.challenge.voting.service.SessionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping("agendas/{agendaId}/sessions")
    public Mono<Session> save(@PathVariable("agendaId") String agendaId, @RequestBody Session session) {
        return service.save(agendaId, session);
    }
}
