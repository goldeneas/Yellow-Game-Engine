package com.yellow.game;

import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;

public class TestGame implements IGame {

    Renderer renderer;

    public TestGame(){
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init("/vertex.vs", "/fragment.fs");
    }

    @Override
    public void update(double deltaTime) {
    }

    @Override
    public void input(Window window) {
    }

    @Override
    public void draw(Window window) {
        renderer.draw();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
