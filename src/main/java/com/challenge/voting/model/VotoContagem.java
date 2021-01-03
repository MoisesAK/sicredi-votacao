package com.challenge.voting.model;

public class VotoContagem {

    private long yes;

    private long no;

    public VotoContagem() {}

    public VotoContagem(long yes, long no) {
        this.yes = yes;
        this.no = no;
    }

    public long getYes() {
        return yes;
    }

    public void setYes(long yes) {
        this.yes = yes;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }
}
