package com.yellow.engine.rendering;

import com.yellow.engine.handlers.ObjectHandler;
import com.yellow.engine.utils.Utils;
import com.yellow.engine.windows.Window;
import com.yellow.engine.world.Camera;
import com.yellow.engine.world.GameObject;

import org.joml.Matrix4f;

public class Renderer {

    private float FOV = (float) Math.toRadians(60);
    private float zNear = 0.01f;
    private float zFar = 1000f;
    
    private Camera camera;
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
        camera = new Camera();
        transform = new Transform();

        shader = new ShaderProgram();
        shader.createVertexShader(Utils.loadResource(vertexPath));
        shader.createFragmentShader(Utils.loadResource(fragmentPath));
        shader.link();

        shader.initUniform("projectionMatrix");
        shader.initUniform("modelViewMatrix");
        shader.initUniform("textureSampler");
    }

    // TODO: Metti il draw del renderer (per i gameobject) in una worker pool con i thread?
    public void draw(Window window){
        window.clearWindow();

        shader.bind();

        // Crea il projectionMatrix
        Matrix4f projectionMatrix = transform.getProjectionMatrix(FOV, window.getWindowWidth(), window.getWindowHeight(), zNear, zFar);
        shader.setUniformValue("projectionMatrix", projectionMatrix);

        // Crea il viewMatrix "globale" (non quello model, cioè per ogni singolo oggetto)
        Matrix4f viewMatrix = transform.getViewMatrix(camera);

        // TODO: Implementa questo sistema (?)
        // Valore messo a 0 perchè siamo solo una texture, altrimenti
        // dovremmo cambiare l'id in base alla texture che è sulla gpu.
        shader.setUniformValue("textureSampler", 0);

        // Crea il modelViewMatrix per ogni gameObject
        for(GameObject gameObj : ObjectHandler.getGameObjects()) {
            Matrix4f modelViewMatrix = transform.getModelViewMatrix(gameObj, viewMatrix);
            shader.setUniformValue("modelViewMatrix", modelViewMatrix);
            gameObj.draw();
        }

        shader.unbind();
    }

    public void dispose(){
        if(shader != null) { shader.dispose(); }
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    
}
