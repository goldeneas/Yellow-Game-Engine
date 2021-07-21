package com.yellow.engine.prefabs;

import com.yellow.engine.rendering.Mesh;
import com.yellow.engine.world.GameObject;

public class Cube extends GameObject{

    private float[] positions = new float[] {
        -0.5f,  0.5f,  0.5f,
        -0.5f, -0.5f,  0.5f,
         0.5f, -0.5f,  0.5f,
         0.5f,  0.5f,  0.5f,
        -0.5f,  0.5f, -0.5f,
         0.5f,  0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
         0.5f, -0.5f, -0.5f,
    };

    private float[] colors = new float[]{
        0.5f, 0.0f, 0.0f,
        0.0f, 0.5f, 0.0f,
        0.0f, 0.0f, 0.5f,
        0.0f, 0.5f, 0.5f,
        0.5f, 0.0f, 0.0f,
        0.0f, 0.5f, 0.0f,
        0.0f, 0.0f, 0.5f,
        0.0f, 0.5f, 0.5f,
    };

    private int[] indexes = new int[] {
        0, 1, 3, 3, 1, 2,
        4, 0, 3, 5, 4, 3,
        3, 2, 7, 5, 3, 7,
        6, 1, 0, 6, 0, 4,
        2, 1, 6, 2, 6, 7,
        7, 6, 4, 7, 4, 5,
    };

    public Cube() {
        super();

        this.mesh = new Mesh(positions, colors, indexes);
        this.mesh.generateBuffers();
    }

}
