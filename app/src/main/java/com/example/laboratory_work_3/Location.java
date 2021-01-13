package com.example.laboratory_work_3;

public class Location {
    public String location, country, state, city;
    public float latitude, longitude;

    @Override
    public String toString() {
        return "location='" + location + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longtitude=" + longitude;
    }
}
