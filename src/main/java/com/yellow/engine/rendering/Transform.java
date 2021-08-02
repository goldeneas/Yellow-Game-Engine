package com.yellow.engine.rendering;

import com.yellow.engine.world.Camera;
import com.yellow.engine.world.GameObject;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform {

    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private Matrix4f modelViewMatrix;

    public Transform() {
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        this.modelViewMatrix = new Matrix4f();
    }

    public Matrix4f getProjectionMatrix(float fov, float windowWidth, float windowHeight, float zNear, float zFar) {
        return projectionMatrix.setPerspective(fov, windowWidth / windowHeight, zNear, zFar);
    }

    public Matrix4f getViewMatrix(Camera camera) {
        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();
        
        // First do the rotation so camera rotates over its position
        viewMatrix.identity()
                .rotateX((float)Math.toRadians(rotation.x))
                .rotateY((float)Math.toRadians(rotation.y))
                .rotateZ((float)Math.toRadians(rotation.z))
                .translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        return viewMatrix;
    }

    public Matrix4f getModelViewMatrix(GameObject gameObject, Matrix4f viewMatrix) {
        Vector3f rotation = gameObject.getRotation();
        modelViewMatrix.identity().translate(gameObject.getPosition())
            .rotateX((float)Math.toRadians(-rotation.x))
            .rotateY((float)Math.toRadians(-rotation.y))
            .rotateZ((float)Math.toRadians(-rotation.z))
            .scale(gameObject.getScale());
        Matrix4f viewCurr = new Matrix4f(viewMatrix);
        return viewCurr.mul(modelViewMatrix);
    }

}
