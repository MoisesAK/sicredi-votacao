package com.prv.votacao.service;

import com.prv.votacao.models.Session;
import com.prv.votacao.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    SessionRepository repository;

    @Override
    public Mono<Session> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Session> save(Session session) {
        return repository.save(session);
    }
}
