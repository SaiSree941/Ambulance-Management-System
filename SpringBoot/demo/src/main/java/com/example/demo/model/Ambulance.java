package com.example.demo.model;

public class Ambulance {
    private int id;
    private double[] location;
    private boolean available;

    public Ambulance(int id, double[] location) {
        this.id = id;
        this.location = location;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public double[] getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setLatitude(double latitude) {
        this.location[0] = latitude;
    }

    public void setLongitude(double longitude) {
        this.location[1] = longitude;
    }
}
