package com.stalinhuallullo.ivr;

public abstract class ItemRule implements Rule {

    private int id;

    public ItemRule(int id) {
        this.id = id;
    }

    public abstract void apply(Session s, WaMessage m);

    public int getId() {
        return id;
    }
}