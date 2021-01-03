package com.challenge.voting.service;

import com.challenge.voting.model.Session;
import com.challenge.voting.model.Vote;
import com.challenge.voting.repository.SessionRepository;
import com.challenge.voting.repository.VoteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class VoteService {

    private final VoteRepository repository;

    private final SessionRepository sessionRepository;

    public VoteService(VoteRepository repository, SessionRepository sessionRepository) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
    }

    public Mono<Long> countBySessionIdAndCpf(final String sessionId, final String cpf) {
        return Mono.empty();
    }

    public Mono<Vote> save(String sessionId, Vote vote) {
        return sessionRepository.findById(sessionId)
                .flatMap(s -> this.validateUserHasVoted(vote, s))
                .flatMap(this::validateExpiredSession)
                .map(vote::withSession)
                .flatMap(repository::save)
                .switchIfEmpty(Mono.error(new RuntimeException("sessao nao existe")));
    }

    private Mono<Session> validateUserHasVoted(Vote vote, Session s) {
        return repository.existsBySessionAgendaIdAndCpf(s.getAgenda().getId(), vote.getCpf())
                .filter(b -> !b)
                .map(igr -> s)
                .switchIfEmpty(Mono.error(new RuntimeException("cpf ja votou nessa pauta")));
    }

    private Mono<Session> validateExpiredSession(Session session) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(session.getInitialDate().plus(session.getMinutes(), ChronoUnit.MINUTES))) {
            return Mono.error(new RuntimeException("sessao expirou"));
        }

        return Mono.just(session);
    }
}
