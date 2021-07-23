package com.yellow.game;

import com.yellow.engine.handlers.ObjectHandler;
import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.prefabs.Cube;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;
import com.yellow.engine.world.Camera;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class TestGame implements IGame {

    private Renderer renderer;


    @Override
    public void init(Window window) throws Exception {
        renderer = new Renderer();
        renderer.init(window, "/shaders/vertex.vs", "/shaders/fragment.fs");

        ObjectHandler.add(new Cube(new Vector3f(0, 0, -10)));
        ObjectHandler.add(new Cube(new Vector3f(1, 0, -10)));
        ObjectHandler.add(new Cube(new Vector3f(2, 0, -10)));
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

        // TODO: Metti un getter per la camera in Window.java (?)

        if(window.isKeyDown(GLFW.GLFW_KEY_D)) {
            Camera.getCamera().move(0.1f, 0);
        }
        else if(window.isKeyDown(GLFW.GLFW_KEY_A)) {
            Camera.getCamera().move(-0.1f, 0);
        }
        else if(window.isKeyDown(GLFW.GLFW_KEY_W)) {
            Camera.getCamera().move(0, 0, -0.1f);
        }
        else if(window.isKeyDown(GLFW.GLFW_KEY_S)) {
            Camera.getCamera().move(0, 0, 0.1f);
        }
        else if(window.isKeyDown(GLFW.GLFW_KEY_Z)) {
            Camera.getCamera().rotate(-0.5f, 0);
        }
        else if(window.isKeyDown(GLFW.GLFW_KEY_X)) {
            Camera.getCamera().rotate(0.5f, 0);
        }
        else if(window.isKeyDown(GLFW.GLFW_KEY_C)) {
            Camera.getCamera().rotate(0, 0.5f, 0);
        }
        else if(window.isKeyDown(GLFW.GLFW_KEY_V)) {
            Camera.getCamera().rotate(0, -0.5f, 0);
        }
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
