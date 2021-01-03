package com.challenge.voting.service;

import com.challenge.voting.model.Session;
import com.challenge.voting.repository.AgendaRepository;
import com.challenge.voting.repository.SessionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SessionService {

    private final SessionRepository repository;

    private final AgendaRepository agendaRepository;

    public SessionService(SessionRepository repository, AgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    public Mono<Session> save(final String agendaId, Session session) {
        return agendaRepository.findById(agendaId)
                .map(session::withAgenda)
                .flatMap(repository::save)
                .switchIfEmpty(Mono.error(new RuntimeException("pauta nao foi encontrada")));
    }
}
