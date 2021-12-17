package com.jules.models;

public enum Status {
    New (0),
    Pending(1),
    Ready(2),
    Done(3);

    public int index;

    Status(int index) {
        this.index = index;
    }
}
