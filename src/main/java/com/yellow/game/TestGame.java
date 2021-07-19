package com.yellow.game;

import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.rendering.Mesh;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;

public class TestGame implements IGame {

    private Renderer renderer;
    private Mesh mesh;

    private float[] positions = {
        -0.5f,  0.5f, 0.0f,
        -0.5f, -0.5f, 0.0f,
         0.5f,  0.5f, 0.0f,
         0.5f,  0.5f, 0.0f,
        -0.5f, -0.5f, 0.0f,
         0.5f, -0.5f, 0.0f,
    };

    @Override
    public void init() throws Exception {
        renderer = new Renderer();
        renderer.init("/vertex.vs", "/fragment.fs");
        
        mesh = new Mesh(positions);
    }

    @Override
    public void update(double deltaTime) {
    }

    @Override
    public void input(Window window) {
    }

    @Override
    public void draw(Window window) {
        renderer.draw(mesh, window);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
