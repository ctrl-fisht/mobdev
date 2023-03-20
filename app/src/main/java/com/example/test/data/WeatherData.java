package com.example.test.data;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WeatherData {

    private Coord coord;

    private Main main;

    private String name;

    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    private ArrayList<WeatherInner> weather;

    public ArrayList<WeatherInner> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherInner> weather) {
        this.weather = weather;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
