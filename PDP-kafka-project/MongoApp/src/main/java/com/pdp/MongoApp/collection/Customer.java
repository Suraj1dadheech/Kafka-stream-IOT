package com.pdp.MongoApp.collection;

public class Customer {


    private int i;
    private String name;

    public Customer() {
    }

    public Customer(int i, String name) {
        this.i = i;
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
