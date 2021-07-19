package com.yellow.engine.rendering;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.*;

// Questa classe deve essere usata solo dopo che Renderer.java
// ha linkato la vertex e la fragment shader.
public class Mesh {

    private int vaoId;
    private int posVboId, idxVboId;

    private int vertexCount;

    private float[] positions;
    private int[] indexes;

    public Mesh(float[] positions, int[] indexes){
        this.positions = positions;
        this.indexes = indexes;
    }

    public void generateMesh(){
        vertexCount = indexes.length;

        try(MemoryStack stack = stackPush()){
            FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
            positionsBuffer.put(positions).flip();

            IntBuffer indexesBuffer = stack.callocInt(indexes.length);
            indexesBuffer.put(indexes).flip();
            
            // Crea il VAO e bindalo
            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);
 
            // Crea il VBO (posizioni) e bindalo
            posVboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Crea il VBO (indici) e bindalo
            idxVboId = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexesBuffer, GL_STATIC_DRAW);

 
            // Unbinda VBO e VAO
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }
    }

    public void dispose(){
        glDisableVertexAttribArray(0);

        // Elimina VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(idxVboId);

        // Elimina VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }

    public int getVaoId(){
        return vaoId;
    }

    public int getVertexCount(){
        return vertexCount;
    }
    
}
