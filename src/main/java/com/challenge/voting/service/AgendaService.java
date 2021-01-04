package com.challenge.voting.service;

import com.challenge.voting.model.Agenda;
import com.challenge.voting.model.Answer;
import com.challenge.voting.repository.AgendaRepository;
import com.challenge.voting.repository.VoteRepository;
import com.challenge.voting.resource.VoteResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AgendaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteResource.class);

    private final AgendaRepository repository;

    private final VoteRepository voteRepository;

    public AgendaService(AgendaRepository repository, VoteRepository voteRepository) {
        this.repository = repository;
        this.voteRepository = voteRepository;
    }

    public Mono<Agenda> findById(String id) {
        return repository.findById(id).flatMap(this::countVotes);
    }

    public Flux<Agenda> findAll() {
        return repository.findAll().flatMap(this::countVotes);
    }

    public Mono<Agenda> save(Agenda agenda) {
        return repository.save(agenda);
    }

    public Mono<Agenda> countVotes(Agenda agenda) {
        LOGGER.debug("counting votes by agenda: {}", agenda);

        Mono<Long> yes = voteRepository.countBySessionAgendaIdAndAnswer(agenda.getId(),Answer.YES);
        Mono<Long> no = voteRepository.countBySessionAgendaIdAndAnswer(agenda.getId(),Answer.NO);

        return yes.zipWith(no).map(agenda::withVotes);
    }
}
