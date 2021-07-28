package com.yellow.game;

import com.yellow.engine.handlers.ChunkHandler;
import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;
import com.yellow.engine.world.Camera;

// DEBUG (WIREFRAME)
import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class TestGame implements IGame {

    private Renderer renderer;

    @Override
    public void init(Window window) throws Exception {
        renderer = new Renderer();
        renderer.init(window, "/shaders/vertex.vs", "/shaders/fragment.fs");

        for(int z = 0; z < 2; z++) {
            for(int x = 0; x < 3; x++) {
                ChunkHandler.generateChunk(new Vector3f(x, 0, z));
            }
        }
    }

    @Override
    public void update(double deltaTime) {
    }

    @Override
    public void input(Window window) {
        Camera camera = renderer.getCamera();

        // DEBUG: Wireframe per controllare le render call
        if (window.isKeyDown(GLFW.GLFW_KEY_F1)) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            glDisable(GL_TEXTURE_2D);
        }
     
        if (window.isKeyDown(GLFW.GLFW_KEY_F2)) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
            glEnable(GL_TEXTURE_2D);
        }

        // Movimento della telecamera
        if(window.isKeyDown(GLFW.GLFW_KEY_D)) {
            camera.move(0.1f, 0);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_A)) {
            camera.move(-0.1f, 0);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_W)) {
            camera.move(0, 0, -0.1f);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_S)) {
            camera.move(0, 0, 0.1f);
        }

        if(window.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            camera.move(0, 0.2f);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            camera.move(0, -0.2f);
        }

        if(window.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            camera.rotate(0, -0.5f);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            camera.rotate(0, 0.5f);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_UP)) {
            camera.rotate(-0.5f, 0);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            camera.rotate(0.5f, 0);
        }
    }

    @Override
    public void draw(Window window) {
        renderer.draw(window);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
