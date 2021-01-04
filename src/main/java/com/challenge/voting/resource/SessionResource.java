package com.challenge.voting.resource;

import com.challenge.voting.model.Session;
import com.challenge.voting.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class SessionResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteResource.class);

    private final SessionService service;

    public SessionResource(SessionService service) {
        this.service = service;
    }

    /*
        Conhecendo a pauta (identificador da pauta) é possível abrir uma sessão para a pauta.
     */
    @PostMapping("agendas/{agendaId}/sessions")
    public Mono<Session> save(@PathVariable("agendaId") String agendaId, @RequestBody Session session) {
        LOGGER.info("create session with agendaId: {}", agendaId);
        return service.save(agendaId, session);
    }
}
