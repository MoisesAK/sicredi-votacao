package com.challenge.voting.model;


import reactor.util.function.Tuple2;

public class Agenda {

    private String id;

    private String title;

    private Long yes;

    private Long no;

    public void setId(String id) {
        this.id = id;
    }

    public Long getYes() {
        return yes;
    }

    public Long getNo() {
        return no;
    }

    public Agenda() {}

    public Agenda(final String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Agenda withVotes(Tuple2<Long,Long> votes) {
        this.yes = votes.getT1();
        this.no = votes.getT2();
        return this;
    }
}
