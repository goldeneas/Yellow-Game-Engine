package com.yellow.game;

import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.rendering.Mesh;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;

public class TestGame implements IGame {

    private Renderer renderer;
    private Mesh mesh;

    private float[] positions = new float[]{
        -0.5f,  0.5f, -1.0f,
        -0.5f, -0.5f, -1.0f,
         0.5f, -0.5f, -1.0f,
         0.5f,  0.5f, -1.0f,
    };

    float[] colours = new float[]{
        0.47f, 0.11f, 0.58f,
        0.87f, 0.39f, 0.28f,
        0.47f, 0.11f, 0.58f,
        0.87f, 0.39f, 0.28f,
    };

    private int[] indexes = new int[]{
        0, 1, 3, 3, 1, 2,
    };

    @Override
    public void init(Window window) throws Exception {
        renderer = new Renderer();
        renderer.init(window, "/vertex.vs", "/fragment.fs");
        
        mesh = new Mesh(positions, colours, indexes);
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
