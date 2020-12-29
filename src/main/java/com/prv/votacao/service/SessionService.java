package com.prv.votacao.service;

import com.prv.votacao.models.Session;
import reactor.core.publisher.Mono;

public interface SessionService {

    Mono<Session> findById(long id);

    Mono<Session> save(Session session);

}
