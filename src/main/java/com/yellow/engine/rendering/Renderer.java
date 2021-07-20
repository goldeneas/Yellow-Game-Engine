package com.yellow.engine.rendering;

import com.yellow.engine.utils.Utils;
import com.yellow.engine.windows.Window;
import com.yellow.engine.world.GameObject;
import com.yellow.engine.world.Transform;

import org.joml.Matrix4f;

public class Renderer {

    private float FOV = (float) Math.toRadians(60);
    private float zNear = 0.01f;
    private float zFar = 1000f;
    private Transform transform;

    private ShaderProgram shader;

    // Metodo opzionale
    public void setOpts(RendererOptions opts){
        this.FOV = opts.FOV;
        this.zFar = opts.Z_FAR;
        this.zNear = opts.Z_NEAR;
    }

    // La path è inizia dalla cartella main/resources
    public void init(Window window, String vertexPath, String fragmentPath) throws Exception{
        transform = new Transform();

        shader = new ShaderProgram();
        shader.createVertexShader(Utils.loadResource(vertexPath));
        shader.createFragmentShader(Utils.loadResource(fragmentPath));
        shader.link();

        shader.initUniform("projectionMatrix");
        shader.initUniform("worldMatrix");
    }

    public void draw(Window window, GameObject[] gameObjects){
        window.clearWindow();

        shader.bind();

        // Crea il projectionMatrix
        Matrix4f projectionMatrix = transform.getProjectionMatrix(FOV, window.getWindowWidth(), window.getWindowHeight(), zNear, zFar);
        shader.setUniformValue("projectionMatrix", projectionMatrix);

        // Crea il worldMatrix per ogni gameObject
        for(GameObject gameObj : gameObjects) {
            Matrix4f worldMatrix = transform.getWorldMatrix(
                gameObj.getPosition(),
                gameObj.getRotation(),
                gameObj.getScale());
            shader.setUniformValue("worldMatrix", worldMatrix);
            gameObj.getMesh().draw();
        }

        shader.unbind();
    }

    public void dispose(){
        if(shader != null) { shader.dispose(); }
    }
    
}
