package com.company.models;

public class ActivityType {
    private int id;
    private String name;

    public ActivityType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ActivityType(String name) {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}