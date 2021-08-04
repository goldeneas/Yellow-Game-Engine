package com.yellow.engine.rendering;

import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL33.*;

public class Mesh {

    private static final Vector3f DEFAULT_COLOR = new Vector3f(1.0f, 1.0f, 1.0f);

    private int VAO;
    private int vertxVbo, idVbo, textVbo, nrmlVbo;

    private int vertexCount;

    private Texture texture;
    private Vector3f color = Mesh.DEFAULT_COLOR;

    protected List<Integer> VBOs;

    public Mesh(float[] vertices, int[] indices, float[] texturePos, float[] normals){
        FloatBuffer verticesBuffer = null;
        IntBuffer indicesBuffer = null;
        FloatBuffer textureBuffer = null;
        FloatBuffer normalsBuffer = null;

        try {

            vertexCount = indices.length;
            VBOs = new ArrayList<>();

            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();

            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();

            textureBuffer = MemoryUtil.memAllocFloat(texturePos.length);
            textureBuffer.put(texturePos).flip();

            normalsBuffer = MemoryUtil.memAllocFloat(normals.length);
            normalsBuffer.put(normals).flip();
            
            // Crea il VAO e bindalo
            VAO = glGenVertexArrays();
            glBindVertexArray(VAO);
 
            // Crea il VBO (posizioni) e bindalo
            vertxVbo = glGenBuffers();
            VBOs.add(vertxVbo);
            glBindBuffer(GL_ARRAY_BUFFER, vertxVbo);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Crea il VBO (texture) e bindalo
            textVbo = glGenBuffers();
            VBOs.add(textVbo);
            glBindBuffer(GL_ARRAY_BUFFER, textVbo);
            glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            // Crea il VBO (normals) e bindalo
            nrmlVbo = glGenBuffers();
            VBOs.add(nrmlVbo);
            glBindBuffer(GL_ARRAY_BUFFER, nrmlVbo);
            glBufferData(GL_ARRAY_BUFFER, normalsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(2);
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);

            // Crea il VBO (indici) e bindalo
            idVbo = glGenBuffers();
            VBOs.add(idVbo);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idVbo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

 
            // Unbinda VBO e VAO
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            // Liberiamo la memoria off-heap.
            if(verticesBuffer != null) { MemoryUtil.memFree(verticesBuffer); }
            if(indicesBuffer != null) { MemoryUtil.memFree(indicesBuffer); }
            if(textureBuffer != null) { MemoryUtil.memFree(textureBuffer); }
            if(normalsBuffer != null) { MemoryUtil.memFree(normalsBuffer); }
        }

    }

    public void draw() {
        if(texture != null) {
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE0, texture.getTextureId());
        }

        glBindVertexArray(this.getVAO());

        glDrawElements(GL_TRIANGLES, this.getVertexCount(), GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        // glBindTexture(GL_TEXTURE_2D, 0);
    }

    // Questo metodo di norma va chiamato con GameObject#dispose() direttamente se assegnato ad un gameobject
    // così si elimina anche dalla lista dei gameobject.
    public void dispose(){
        // Disattiva gli attributi del VAO
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);

        // Elimina VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        for(int vbo : VBOs) {
            glDeleteBuffers(vbo);
        }

        // Elimina la texture
        if(texture != null) { texture.dispose(); }

        // Elimina VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(VAO);
    }

    public int getVAO(){
        return VAO;
    }

    public int getVertexCount(){
        return vertexCount;
    }

    public Vector3f getColor() {
        return color;
    }

    public boolean isTextured() {
        return this.texture != null;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    
}
