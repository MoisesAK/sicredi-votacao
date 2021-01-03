package com.challenge.voting.resource;

import com.challenge.voting.model.Vote;
import com.challenge.voting.service.SessionService;
import com.challenge.voting.service.VoteService;
import org.springframework.web.bind.annotation.*;
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

    /*

     */
    @PostMapping("sessoes/{sessaoId}/votos")
    public Mono<Vote> save(@PathVariable("sessaoId") String sessaoId, @RequestBody Vote vote) {

        return service.save(sessaoId, vote);
    }

    @GetMapping("lalala/{sessionId}/{cpf}")
    public Mono<Long> countCpf (@PathVariable("sessionId") String sessionId, @PathVariable("cpf") String cpf ) {
        //antes ver se a sessão ainda está aberta
        //se estiver aberta ver se ja votou

        return service.countBySessionIdAndCpf(sessionId, cpf);
    }

//    @GetMapping("sessions/{sessionId}/votes")
//    public Mono<VotoContagem> countBySessionId (@PathVariable("sessionId") String sessionId) {

//        return service.countBySessionId(sessionId);
//    }

    @GetMapping("votes")
    public Flux<Vote> findAll() {
        return service.findAll();
    }
}


