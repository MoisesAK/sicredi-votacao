package com.prv.votacao.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_PAUTA")
@Getter
@Setter
public class Pauta implements Serializable {

    public static final long SerialVercionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;

    private long tempo;

}
