package com.reflection;

import java.util.Random;

@CustomAnnotation(name = "name", value = "value")
public class Reflection implements Comparable<Reflection> {

    private int id;

    public String name;

    public Reflection(int id) {
        this.id = id;
    }

    public Reflection(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String calculateRandomName() {
        return name + new Random().nextInt(100);
    }

    @Override
    public int compareTo(Reflection o) {
        return id - o.getId();
    }

}
