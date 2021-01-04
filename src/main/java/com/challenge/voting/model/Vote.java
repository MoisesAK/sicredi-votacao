package com.challenge.voting.model;


public class Vote {

    private String id;

    private String cpf;

    private Answer answer;

    private Session session;

    public Vote() {}

    public Vote(String cpf, Answer answer, Session session) {
        this.cpf = cpf;
        this.answer = answer;
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Vote withSession(Session session) {
        this.session = session;
        return this;
    }
}
