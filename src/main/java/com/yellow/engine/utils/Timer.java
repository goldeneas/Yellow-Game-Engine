package com.yellow.engine.utils;

public class Timer {

    private double lastTime;

    public Timer(){
        lastTime = getTime();
    }

    public double getTime(){
        return System.nanoTime() / 1_000_000_000.0;
    }

    public double getElapsedTime(){
        double currentTime = getTime();
        float elapsedTime = (float) (currentTime - lastTime);

        lastTime = currentTime;
        return elapsedTime;
    }

    public double getLastTime(){
        return lastTime;
    }
    
}
