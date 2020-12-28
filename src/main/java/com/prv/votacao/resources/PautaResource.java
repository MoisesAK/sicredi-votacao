package com.prv.votacao.resources;

import com.prv.votacao.models.Pauta;
import com.prv.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController("pauta")
public class PautaResource {

    @Autowired
    PautaRepository repository;

    @GetMapping("{id}")
    public Mono<Pauta> findById(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @GetMapping
    public Flux<Pauta> listAgenda(){
        return repository.findAll();
    }

    @PostMapping
    public Mono<Pauta> createAgenda(@RequestBody Pauta pauta){
        return repository.save(pauta);
    }
    //endofucketor dentro da categoria dos tipos que mapeia para ele mesmo == promise do javascripto
    //flux é uma lista de promise do javascripto feito pelo andam sendler
}
