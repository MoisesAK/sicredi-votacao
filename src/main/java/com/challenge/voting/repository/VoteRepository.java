package com.challenge.voting.repository;

import com.challenge.voting.model.Answer;
import com.challenge.voting.model.Vote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface VoteRepository extends ReactiveMongoRepository<Vote, String> {
    Mono<Boolean> existsBySessionAgendaIdAndCpf(final String id, final String cpf);
    Mono<Long> countBySessionAgendaIdAndAnswer(final String id, final Answer answer);
}
