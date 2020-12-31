package com.challenge.voting.model;

public class VoteCount {

    private long yes;

    private long no;

    public VoteCount() {}

    public VoteCount(long yes, long no) {
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
