package com.example.test;

public class City {
    public int id;
    public String city;

    public City(int id, String city) {
        this.id = id;
        this.city = city;
    }

    @Override
    public String toString() {
        return city;
    }
}
