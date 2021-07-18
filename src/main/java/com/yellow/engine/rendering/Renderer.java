package com.yellow.engine.rendering;

import static org.lwjgl.opengl.GL33.*;

import com.yellow.engine.utils.Utils;

public class Renderer {

    ShaderProgram shader;
    
    public void init(String vertexPath, String fragmentPath) throws Exception{
        shader = new ShaderProgram();
        shader.createVertexShader(Utils.loadResource(vertexPath));
        shader.createVertexShader(Utils.loadResource(fragmentPath));
        shader.link();
    }

    public void draw(){
        shader.bind();
        shader.unbind();
    }

    public void dispose(){
        if(shader != null) { shader.dispose(); }
    }
    
}
