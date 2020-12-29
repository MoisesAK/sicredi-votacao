package com.prv.votacao.models;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private LocalDateTime initialDate;

    private long minutes;

    private Mono<Agenda> agenda;

    public Long getId() {
        return id;
    }

    public Session(Long id, LocalDateTime initialDate, long minutes, Mono<Agenda> agenda) {
        this.id = id;
        this.initialDate = initialDate;
        this.minutes = minutes;
        this.agenda = agenda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public Mono<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(Mono<Agenda> agenda) {
        this.agenda = agenda;
    }


}
