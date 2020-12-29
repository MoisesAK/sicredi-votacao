package com.challenge.voting.service;

import com.challenge.voting.repository.SessionRepository;
import com.challenge.voting.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SessionService {

    @Autowired
    SessionRepository repository;

    public Mono<Session> findById(String id) {
        return repository.findById(id);
    }

    public Flux<Session> findAll() { return repository.findAll(); }

    public Mono<Session> save(Session session) {
        return repository.save(session);
    }
}
