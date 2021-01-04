package com.challenge.voting.resource;

import com.challenge.voting.model.Vote;
import com.challenge.voting.service.SessionService;
import com.challenge.voting.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class VoteResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteResource.class);

    private final VoteService service;

    private final SessionService serviceSession;

    public VoteResource(VoteService service, SessionService serviceSession) {
        this.service = service;
        this.serviceSession = serviceSession;
    }

    @PostMapping("sessions/{sessionId}/votes")
    public Mono<Vote> save(@PathVariable("sessionId") String sessionId, @RequestBody Vote vote) {
        LOGGER.info("register vote in session and vote.cpf: '{}' and '{}'",sessionId, vote.getCpf());
        return service.save(sessionId, vote);
    }
}


