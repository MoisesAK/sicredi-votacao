package com.prv.votacao.resources;

import com.prv.votacao.models.Pauta;
import com.prv.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PautaResource {

    @Autowired
    PautaRepository repository;

    @GetMapping("/pauta/{id}")
    public Pauta findById(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @GetMapping("/pauta/")
    public List<Pauta> listPautas(){
        return repository.findAll();
    }

    @PostMapping("/pauta")
    public Pauta createPauta(@RequestBody Pauta pauta){
        return repository.save(pauta);
    }


}
