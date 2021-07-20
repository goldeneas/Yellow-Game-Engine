package com.yellow.engine.interfaces;

import com.yellow.engine.windows.Window;

public interface IGame {

    void init(Window window) throws Exception;

    void update(double deltaTime);

    void input(Window window);

    void draw(Window window);

    void dispose();

}
