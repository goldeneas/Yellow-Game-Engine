package com.yellow.game;

import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.prefabs.Cube;
import com.yellow.engine.rendering.Renderer;
import com.yellow.engine.windows.Window;
import com.yellow.engine.world.GameObject;

public class TestGame implements IGame {

    private Renderer renderer;

    // TODO: Conviene trasformare questa lista in un ArrayList ad esempio
    // Così è più facile aggiungere e rimuovere gli elementi (ed evitare un NPE).
    private GameObject[] objs = new GameObject[1];


    @Override
    public void init(Window window) throws Exception {
        renderer = new Renderer();
        renderer.init(window, "/vertex.vs", "/fragment.fs");

        objs[0] = new Cube();
        objs[0].move(0, 0, -10);
    }

    @Override
    public void update(double deltaTime) {
    }

    @Override
    public void input(Window window) {
    }

    @Override
    public void draw(Window window) {
        renderer.draw(window, objs);
        float rotation = objs[0].getRotation().x + 1.5f;
        if ( rotation > 360 ) {
            rotation = 0;
        }
        objs[0].setRotation(rotation, rotation, rotation);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
