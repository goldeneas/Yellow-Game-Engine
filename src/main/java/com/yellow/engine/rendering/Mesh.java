package com.yellow.engine.rendering;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;

// Questa classe deve essere usata solo dopo che Renderer.java
// ha linkato la vertex e la fragment shader.
public class Mesh {

    private int vaoId, vboId;
    private int vertexCount;
    private float[] positions;

    public Mesh(float[] positions){
        this.positions = positions;
    }

    public void generateMesh(){
        vertexCount = positions.length / 3;

        try(MemoryStack stack = stackPush()){
            FloatBuffer verticesBuffer = stack.callocFloat(positions.length);
            verticesBuffer.put(positions).flip();
            
            // Crea il VAO e bindalo
            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);
 
            // Crea il VBO e bindalo
            vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
 
            // Crea posizione 0 per la shader
            // (posizione dei vertici)
            glEnableVertexAttribArray(0);
 
            // Definisci la struttura dell'array di vertici
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
 
            // Unbinda VBO e VAO
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }
    }

    public void dispose(){
        glDisableVertexAttribArray(0);

        // Elimina VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboId);

        // Elimina VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }

    public int getVaoId(){
        return vaoId;
    }

    public int getVboId(){
        return vboId;
    }

    public int getVertexCount(){
        return vertexCount;
    }
    
}
