package com.challenge.voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Answer {
    @JsonProperty("SIM")
    YES,

    @JsonProperty("NÃO")
    NO
}
