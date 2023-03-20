package com.example.test.data;

public class Coord {
    public Coord() {
        this.lat = 0;
        this.lon = 0;
    }

    public Coord(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    private float lat;
    private float lon;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lng) {
        this.lon = lng;
    }
}
