package com.yellow.engine.utils;

public class Color {

    public float r, g, b, a;

    public Color(float r, float g, float b, float a) {
        this.r = r / 255f;
        this.g = g / 255f;
        this.b = b / 255f;
        this.a = a / 255f;
    }

}
