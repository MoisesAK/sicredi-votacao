package com.prv.votacao.repository;

import com.prv.votacao.models.Pauta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PautaRepository extends ReactiveMongoRepository<Pauta, Long> {}
