package com.stalinhuallullo.ivr;

public interface Rule {
    public void apply(Session s, WaMessage m);
}
