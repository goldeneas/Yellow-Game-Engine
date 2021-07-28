package com.yellow.engine.prefabs.base;

import com.yellow.engine.handlers.TextureHandler;
import com.yellow.engine.rendering.Mesh;
import com.yellow.engine.rendering.Texture;
import com.yellow.engine.world.GameObject;

public class Block extends GameObject{

    float[] vertices = new float[] {
        // Front Face
        -0.5f,  0.5f,  0.5f, // V0
        -0.5f, -0.5f,  0.5f, // V1
         0.5f, -0.5f,  0.5f, // V2
         0.5f,  0.5f,  0.5f, // V3

        // Top Face
        -0.5f, 0.5f, -0.5f, // V4
         0.5f, 0.5f, -0.5f, // V5
        -0.5f, 0.5f,  0.5f, // V6
         0.5f, 0.5f,  0.5f, // V7
        
        // Right Face
         0.5f,  0.5f,  0.5f, // V8
         0.5f, -0.5f,  0.5f, // V9
         0.5f, -0.5f, -0.5f, // V10
         0.5f,  0.5f, -0.5f, // V11

        // Left Face
        -0.5f,  0.5f,  0.5f, // V12
        -0.5f,  0.5f, -0.5f, // V13
        -0.5f, -0.5f, -0.5f, // V14
        -0.5f, -0.5f,  0.5f, // V15
        
        // Bottom Face
        -0.5f, -0.5f, -0.5f, // V16
        -0.5f, -0.5f,  0.5f, // V17
         0.5f, -0.5f, -0.5f, // V18
         0.5f, -0.5f,  0.5f, // V19

        // Back Face
        -0.5f,  0.5f, -0.5f, // V20
         0.5f,  0.5f, -0.5f, // V21
        -0.5f, -0.5f, -0.5f, // V22
         0.5f, -0.5f, -0.5f, // V23
    };

    float[] texturePos = new float[]{
        // Front Face
        0.0f, 0.0f,
        0.0f, 0.5f,
        0.5f, 0.5f,
        0.5f, 0.0f,

        // Top Face
        0.0f, 0.5f,
        0.5f, 0.5f,
        0.0f, 1.0f,
        0.5f, 1.0f,

        // Right Face
        0.0f, 0.0f,
        0.0f, 0.5f,
        0.5f, 0.5f,
        0.5f, 0.0f,

        // Left Face
        0.5f, 0.0f,
        0.0f, 0.0f,
        0.0f, 0.5f,
        0.5f, 0.5f,

        // Bottom Face
        0.5f, 0.5f,
        0.5f, 0.0f,
        1.0f, 0.5f,
        1.0f, 0.0f,

        // Back Face
        0.5f, 0.0f,
        0.0f, 0.0f,
        0.5f, 0.5f,
        0.0f, 0.5f,
    };

    int[] indices = new int[]{
        0,  1,  3,  3,  1,  2,  // Front Face
        7,  5,  6,  6,  5,  4,  // Top Face
        8,  9,  11, 11, 9,  10, // Right Face
        12, 13, 15, 15, 13, 14, // Left Face
        19, 17, 18, 18, 17, 16, // Bottom Face
        20, 21, 22, 22, 21, 23  // Back Face
    };

    private Texture texture;

    public Block() {
        super();

        this.texture = TextureHandler.getTexture("grassblock.png");
        this.mesh = new Mesh(vertices, indices, texturePos, texture);
        this.mesh.generateBuffers();
    }

}
