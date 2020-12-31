package com.challenge.voting.resource;

import com.challenge.voting.model.Vote;
import com.challenge.voting.model.VoteCount;
import com.challenge.voting.service.SessionService;
import com.challenge.voting.service.VoteService;
import org.springframework.web.bind.annotation.*;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VoteResource {

    private final VoteService service;

    private final SessionService serviceSession;

    public VoteResource(VoteService service, SessionService serviceSession) {
        this.service = service;
        this.serviceSession = serviceSession;
    }

    @PostMapping("sessions/{sessionId}/votes")
    public Mono<Vote> save(@PathVariable("sessionId") String sessionId,@RequestBody Vote vote) {
        //implementar se a sessao existe
        //implementar se ja votou
        //salvar voto
        vote.setSessionId(sessionId);
        return service.save(vote);
    }

    @GetMapping("sessions/{sessionId}/votes")
    public Mono<VoteCount> countBySessionId (@PathVariable("sessionId") String sessionId) {
        return service.countBySessionId(sessionId);
    }

    @GetMapping("sessions/{sessionId}/votes/{CPF}")
    public Vote findById(@PathVariable("sessionId") String sessionId, @PathVariable("CPF") String CPF) {
        return null;
    }

    @GetMapping("votes")
    public Flux<Vote> findAll() {
        return service.findAll();
    }
}


