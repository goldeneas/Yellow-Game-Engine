package com.yellow.engine.prefabs;

import com.yellow.engine.rendering.Mesh;
import com.yellow.engine.rendering.Texture;
import com.yellow.engine.world.GameObject;

import org.joml.Vector3f;

public class Cube extends GameObject{

    float[] positions = new float[] {
        -0.5f,  0.5f,  0.5f, // V0
        -0.5f, -0.5f,  0.5f, // V1
         0.5f, -0.5f,  0.5f, // V2
         0.5f,  0.5f,  0.5f, // V3
        -0.5f,  0.5f, -0.5f, // V4
         0.5f,  0.5f, -0.5f, // V5
        -0.5f, -0.5f, -0.5f, // V6
         0.5f, -0.5f, -0.5f, // V7
        
        // For text coords in top face
        -0.5f, 0.5f, -0.5f, // V8: V4 repeated
         0.5f, 0.5f, -0.5f, // V9: V5 repeated
        -0.5f, 0.5f,  0.5f, // V10: V0 repeated
         0.5f, 0.5f,  0.5f, // V11: V3 repeated

        // For text coords in right face
        0.5f,  0.5f, 0.5f, // V12: V3 repeated
        0.5f, -0.5f, 0.5f, // V13: V2 repeated

        // For text coords in left face
        -0.5f,  0.5f, 0.5f, // V14: V0 repeated
        -0.5f, -0.5f, 0.5f, // V15: V1 repeated

        // For text coords in bottom face
        -0.5f, -0.5f, -0.5f, // V16: V6 repeated
         0.5f, -0.5f, -0.5f, // V17: V7 repeated
        -0.5f, -0.5f,  0.5f, // V18: V1 repeated
         0.5f, -0.5f,  0.5f, // V19: V2 repeated
    };

    float[] texturePos = new float[]{
        // Front Face
        0.0f, 0.0f,
        0.0f, 0.5f,
        0.5f, 0.5f,
        0.5f, 0.0f,
        
        // Behind Face
        0.0f, 0.0f,
        0.5f, 0.0f,
        0.0f, 0.5f,
        0.5f, 0.5f,
        
        // Top Face
        0.0f, 0.5f,
        0.5f, 0.5f,
        0.0f, 1.0f,
        0.5f, 1.0f,

        // Right Face
        0.0f, 0.0f,
        0.0f, 0.5f,

        // Left Face
        0.5f, 0.0f,
        0.5f, 0.5f,

        // Bottom Face
        0.5f, 0.0f,
        1.0f, 0.0f,
        0.5f, 0.5f,
        1.0f, 0.5f,
    };

    int[] indexes = new int[]{
        0,  1,  3,  3,  1,  2,  // Front face
        8,  10, 11, 9,  8,  11, // Top Face
        12, 13, 7,  5,  12, 7,  // Right face
        14, 15, 6,  4,  14, 6,  // Left face
        16, 18, 19, 17, 16, 19, // Bottom face
        4,  6,  7,  5,  4,  7   // Back face
    };

    private Texture texture;

    public Cube() {
        super();

        this.texture = new Texture("/textures/grassblock.png");
        this.mesh = new Mesh(positions, indexes, texturePos, texture);
        this.mesh.generateBuffers();
    }

    public Cube(Vector3f position) {
        super(position);

        this.texture = new Texture("/textures/grassblock.png");
        this.mesh = new Mesh(positions, indexes, texturePos, texture);
        this.mesh.generateBuffers();
    }

}
