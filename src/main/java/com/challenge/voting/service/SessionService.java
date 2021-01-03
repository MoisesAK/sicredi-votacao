package com.challenge.voting.service;

import com.challenge.voting.model.Session;
import com.challenge.voting.repository.ScheduleRepository;
import com.challenge.voting.repository.SessionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SessionService {

    private final SessionRepository repository;

    private final ScheduleRepository scheduleRepository;

    public SessionService(SessionRepository repository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    public Mono<Session> findById(String id) {
        return repository.findById(id);
    }

    public Flux<Session> findAll() { return repository.findAll(); }

    public Mono<Session> save(final String agendaId, Session session) {
        return scheduleRepository.findById(agendaId)
                .map(session::withAgenda)
                .flatMap(repository::save)
                .switchIfEmpty(Mono.error(new RuntimeException("pauta nao foi encontrada")));
    }
}
