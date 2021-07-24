package com.yellow.engine.rendering;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Mesh {

    private int vaoId;
    private int posVboId, idxVboId, textVboId;
    private List<Integer> vboIds;

    private int vertexCount;

    private float[] positions, texturePos;
    private int[] indexes;

    private Texture texture;

    public Mesh(float[] positions, int[] indexes, float[] texturePos, Texture texture){
        this.positions = positions;
        this.texturePos = texturePos;
        this.indexes = indexes;

        // TODO: Sostituisci l'ultimo null con una texture ad esempio viola.
        this.texture = texture;
    }

    // Questo metodo è fondamentale per le draw call
    // Però deve essere chiamato dopo Renderer#init altrimenti le shader non sono linkate
    public void generateBuffers() {
        vertexCount = indexes.length;
        vboIds = new ArrayList<>();

        try(MemoryStack stack = stackPush()){
            FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
            positionsBuffer.put(positions).flip();

            FloatBuffer textureBuffer = stack.callocFloat(texturePos.length);
            textureBuffer.put(texturePos).flip();

            IntBuffer indexesBuffer = stack.callocInt(indexes.length);
            indexesBuffer.put(indexes).flip();
            
            // Crea il VAO e bindalo
            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);
 
            // Crea il VBO (posizioni) e bindalo
            posVboId = glGenBuffers();
            vboIds.add(posVboId);
            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Crea il VBO (colori) e bindalo
            textVboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, textVboId);
            glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            // Crea il VBO (indici) e bindalo
            idxVboId = glGenBuffers();
            vboIds.add(posVboId);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexesBuffer, GL_STATIC_DRAW);

 
            // Unbinda VBO e VAO
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } catch(Exception e) {
            // Questo catch serve per Texture#loadImage, altrimenti ogni volta che
            // chiamiamo generateBuffers bisogna aggiungere un throws Exception ai prefab, per esempio.
            e.printStackTrace();
        }
    }

    public void draw() {
        // TODO: controlla perchè TEXTURE0
        // Penso perchè decidiamo di bindare TEXTURE0 a quella texture.
        // Se avessimo messo un'altra texture a GL_TEXTURE1 ne avrebbe usata un'altra
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE0, texture.getTextureId());

        glBindVertexArray(this.getVaoId());

        glDrawElements(GL_TRIANGLES, this.getVertexCount(), GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
    }

    // Questo metodo di norma va chiamato con GameObject#dispose() direttamente se assegnato ad un gameobject
    // così si elimina anche dalla lista dei gameobject.
    public void dispose(){
        // Disattiva gli attributi del VAO
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        // Elimina VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        for(int vboId : vboIds) {
            glDeleteBuffers(vboId);
        }

        // Elimina la texture
        texture.dispose();

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
