package com.zust.stkdy.testsystem.common;

import org.python.antlr.ast.Str;

public class UserPair {
    private float key;
    private float value;

    public UserPair() {
    }

    public UserPair(float key, float value) {
        this.key = key;
        this.value = value;
    }

    public float getKey() {
        return key;
    }

    public void setKey(float key) {
        this.key = key;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
