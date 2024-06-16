package com.stalinhuallullo.ivr;

import java.util.HashMap;

public class Secuence {
    public HashMap<Integer, ItemRule> chain = new HashMap<Integer, ItemRule>();

    public void addRule(ItemRule rule) {

        System.out.println("addRule ====================>");
        chain.put(rule.getId(), rule);
    }

    public ItemRule getRute(int id) {
        return chain.get(id);
    }
}