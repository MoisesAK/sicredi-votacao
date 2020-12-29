package com.prv.votacao.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Agenda {

    private long id;

    private String titulo;

    private long tempo;

    public Agenda(final long id, final String titulo, final long tempo) {
        this.id = id;
        this.titulo = titulo;
        this.tempo = tempo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }
}
