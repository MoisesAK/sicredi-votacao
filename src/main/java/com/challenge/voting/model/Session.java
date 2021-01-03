package com.challenge.voting.model;


import java.time.LocalDateTime;


public class Session {

    private String id;

    private LocalDateTime initialDate;

    private Long minutes;

    private Agenda agenda;

    public Session() {
        this.initialDate = LocalDateTime.now();
        this.minutes = 1L;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes == null || minutes <= 0 ? 1 : minutes;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Session withAgenda(Agenda agenda) {
        this.agenda = agenda;
        return this;
    }
}
