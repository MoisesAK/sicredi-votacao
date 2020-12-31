package com.challenge.voting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vote {

    @Id
    @Indexed
    private String id;

    private String cpf;

    private String sessionId;

    private boolean answer;

    public Vote() {}

    public Vote(String id, String cpf, String sessionId, boolean answer) {
        this.id = id;
        this.cpf = cpf;
        this.sessionId = sessionId;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
