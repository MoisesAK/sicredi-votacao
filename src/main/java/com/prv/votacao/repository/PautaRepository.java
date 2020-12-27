package com.prv.votacao.repository;

import com.prv.votacao.models.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Pauta findById(long id);

}
