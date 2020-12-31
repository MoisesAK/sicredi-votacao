package com.challenge.voting.service;

import com.challenge.voting.model.Vote;
import com.challenge.voting.model.VoteCount;
import com.challenge.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VoteService {

    @Autowired
    VoteRepository repository;

    public Mono<Vote> save(Vote id) {
        return repository.save(id);
    }

    public Mono<Vote> findById(String id) {
        return repository.findById(id);
    }

    public Mono<VoteCount> countBySessionId(final String sessionId) {
        final Mono<Long> yes = repository.countBySessionIdAndAnswer(sessionId, true);
        final Mono<Long> no = repository.countBySessionIdAndAnswer(sessionId, false);

        return yes.zipWith(no).map(t -> new VoteCount(t.getT1(), t.getT2()));
    }



    public Flux<Vote> findAll() {
        return repository.findAll();
    }
}
