package com.yellow.game;

import com.yellow.engine.handlers.ObjectHandler;
import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.prefabs.Cube;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;
import com.yellow.engine.world.Camera;

// DEBUG
import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class TestGame implements IGame {

    private Renderer renderer;

    // TODO: Rendi la creazione di un gameobject più severa. Vogliamo una texture.
    // Possiamo anche provare a passare gli argomenti attarverso l'ObjectHandler
    // Es. ObjectHandler#add(GameOBject tipoDIoGgetto, Vector3f posizione) -> chiama il costruttore del gameobject e imposta lui la posizione del gameobject, così
    // non dobbiamo implementare diversi costruttori per ogni prefab.

    // TODO: Crea una classe che conservi le texture caricate dalla memoria

    @Override
    public void init(Window window) throws Exception {
        renderer = new Renderer();
        renderer.init(window, "/shaders/vertex.vs", "/shaders/fragment.fs");


        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 16; y++) {
                for(int z = 0; z < 16; z++) {
                    ObjectHandler.add(new Cube(new Vector3f(x, y, z)));
                }
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
        if(window.isKeyDown(GLFW.GLFW_KEY_Z)) {
            camera.rotate(-0.5f, 0);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_X)) {
            camera.rotate(0.5f, 0);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_C)) {
            camera.rotate(0, 0.5f, 0);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_V)) {
            camera.rotate(0, -0.5f, 0);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            camera.move(0, 0.5f);
        }
        if(window.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            camera.move(0, -0.5f);
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
