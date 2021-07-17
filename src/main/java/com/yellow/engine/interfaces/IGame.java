package com.yellow.engine.interfaces;

import com.yellow.engine.Window;

public interface IGame {

    void init();
    void update(double deltaTime);
    void input(Window window);
    void draw(Window window);
    void dispose();
    
}
