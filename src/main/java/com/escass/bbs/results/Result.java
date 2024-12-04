package com.escass.bbs.results;

public interface Result {
    String NAME = "result";
    String RESULT = "index";

    String name();

    default String nameToLower() {
        return this.name().toLowerCase();
    }
}
