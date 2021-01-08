package com.demo.horoscope.Models;

import android.graphics.drawable.Drawable;

public class HoroscopeModel {

    public String title;
    public Drawable drawable;
    public int color;

    public HoroscopeModel(String title, Drawable drawable, int color){
        this.title = title;
        this.drawable = drawable;
        this.color = color;
    }
}
