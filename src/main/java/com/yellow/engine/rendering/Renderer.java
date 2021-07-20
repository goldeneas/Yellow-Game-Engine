package com.yellow.engine.rendering;

import com.yellow.engine.utils.Utils;
import com.yellow.engine.windows.Window;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL33.*;
public class Renderer {

    private float FOV = (float) Math.toRadians(60);
    private float Z_NEAR = 0.01f;
    private float Z_FAR = 1000f;
    private Matrix4f projectionMatrix;

    private ShaderProgram shader;

    // Metodo opzionale
    public void setOpts(RendererOptions opts){
        this.FOV = opts.FOV;
        this.Z_FAR = opts.Z_FAR;
        this.Z_NEAR = opts.Z_NEAR;
    }

    // La path è inizia dalla cartella main/resources
    public void init(Window window, String vertexPath, String fragmentPath) throws Exception{
        float aspectRatio = (float) window.getWindowWidth()/window.getWindowHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);

        shader = new ShaderProgram();
        shader.createVertexShader(Utils.loadResource(vertexPath));
        shader.createFragmentShader(Utils.loadResource(fragmentPath));
        shader.link();

        shader.initUniform("projectionMatrix");
    }

    public void draw(Mesh mesh, Window window){
        window.clearWindow();
        mesh.generateMesh();

        shader.bind();
        shader.setUniformValue("projectionMatrix", projectionMatrix);

        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);

        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

        shader.unbind();
    }

    public void dispose(){
        if(shader != null) { shader.dispose(); }
    }
    
}
