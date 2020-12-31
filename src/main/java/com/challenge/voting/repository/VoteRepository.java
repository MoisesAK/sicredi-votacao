package com.challenge.voting.repository;

import com.challenge.voting.model.Vote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface VoteRepository extends ReactiveMongoRepository<Vote, String> {
    Mono<Long> countBySessionIdAndAnswer(final String sessionId, final boolean answer);
}
