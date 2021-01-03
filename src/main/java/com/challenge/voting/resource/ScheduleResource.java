package com.challenge.voting.resource;

import com.challenge.voting.model.Agenda;
import com.challenge.voting.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("pautas")
public class ScheduleResource {

    private final ScheduleService service;

    public ScheduleResource(ScheduleService service) {
        this.service = service;
    }

    @GetMapping("{pautaId}")
    public Mono<Agenda> findById(@PathVariable(value = "pautaId") String id){
        return service.findById(id);
    }

    @GetMapping
    public Flux<Agenda> findAll(){
        return service.findAll();
    }

    /*
        Esse é o início do fluxo. Aqui iniciamos uma pauta.
    */
    @PostMapping
    public Mono<Agenda> save(@RequestBody Agenda agenda) {
        return service.save(agenda);
    }
}
