package com.challenge.voting.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Session {

    @Id
    @Indexed
    private String id;

    private LocalDateTime initialDate;

    private long minutes;

    private String agendaId;

    public Session() {
        this.initialDate = LocalDateTime.now();
    }

    public Session(String id, long minutes, String agendaId) {
        this();
        this.id = id;
        this.minutes = minutes;
        this.agendaId = agendaId;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public String getAgenda() {
        return agendaId;
    }

    public Session withAgendaId(String agendaId) {
       this.agendaId = agendaId;
       return this;
    }


}
