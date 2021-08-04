package com.yellow.engine.prefabs.base;

import com.yellow.engine.handlers.MeshHandler;
import com.yellow.engine.handlers.TextureHandler;
import com.yellow.engine.world.GameObject;

public class Block extends GameObject{

    public Block() {
        super();

        this.mesh = MeshHandler.loadMesh("bunny.obj");
        this.mesh.setTexture(TextureHandler.getTexture("grassblock.png"));
    }

}
