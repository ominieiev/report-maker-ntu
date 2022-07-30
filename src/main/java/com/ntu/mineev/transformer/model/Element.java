package com.ntu.mineev.transformer.model;

public class Element {
    String name;
    String value;

    public String getName() {
        return name;
    }

    public Element() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Element(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
