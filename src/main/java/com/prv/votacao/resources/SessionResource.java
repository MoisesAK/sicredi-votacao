package com.prv.votacao.resources;

import com.prv.votacao.models.Agenda;
import com.prv.votacao.models.Session;
import com.prv.votacao.service.AgendaService;
import com.prv.votacao.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
public class SessionResource {

    @Autowired
    SessionService service;

    AgendaService agendaService;

    @GetMapping("sessoes/{sessionId}")
    public Mono<Session> findByid(@PathVariable("sessionId") long id){
        return service.findById(id);
    }

    @PostMapping("pauta/{id}/sessao")
    public Mono<Session> save(@PathVariable("id") long id, @RequestBody Session session){
        Mono<Agenda> agenda = agendaService.findById(id);

        if(agenda==null){
            return null;//Agenda nao encontrada
        }

        session.setAgenda(agenda);
        return service.save(session);
    }
}
