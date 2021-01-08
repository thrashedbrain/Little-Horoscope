package com.demo.horoscope.Models;

public class HoroscopeResponseModel {
    public String description, compat, mood, color, number, time;

    public HoroscopeResponseModel(String description, String compat, String mood, String color, String number, String time){
        this.description = description;
        this.compat = compat;
        this.mood = mood;
        this.color = color;
        this.number = number;
        this.time = time;
    }
}
