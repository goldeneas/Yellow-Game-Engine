package com.yellow.game;

import com.yellow.engine.handlers.ObjectHandler;
import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.prefabs.Cube;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;
import com.yellow.engine.world.GameObject;

import org.lwjgl.glfw.GLFW;

public class TestGame implements IGame {

    private Renderer renderer;


    @Override
    public void init(Window window) throws Exception {
        renderer = new Renderer();
        renderer.init(window, "/shaders/vertex.vs", "/shaders/fragment.fs");

        ObjectHandler.add(new Cube()).move(0, 0, -10);
    }

    @Override
    public void update(double deltaTime) {
    }

    @Override
    public void input(Window window) {
    }

    @Override
    public void draw(Window window) {
        renderer.draw(window);

        GameObject testObject = ObjectHandler.get(0);
        if(window.isKeyDown(GLFW.GLFW_KEY_D)) {
            float rotation = testObject.getRotation().x + 1.5f;
            if ( rotation > 360 ) {
                rotation = 0;
            }
            testObject.setRotation(rotation, rotation, rotation);
        }
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
