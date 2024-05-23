package com.example.demo.model;

public class Patient {
    private int id;
    private double[] location;
    private int priority;

    public Patient(int id, double[] location, int priority) {
        this.id = id;
        this.location = location;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public double[] getLocation() {
        return location;
    }

    public int getPriority() {
        return priority;
    }
}
