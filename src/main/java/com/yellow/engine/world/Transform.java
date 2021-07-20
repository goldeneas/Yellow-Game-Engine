package com.yellow.engine.world;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform {

    private Matrix4f projectionMatrix;
    private Matrix4f worldMatrix;

    public Transform() {
        this.projectionMatrix = new Matrix4f();
        this.worldMatrix = new Matrix4f();
    }

    public Matrix4f getProjectionMatrix(float fov, float windowWidth, float windowHeight, float zNear, float zFar) {
        float aspectRation = windowWidth / windowHeight;
        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspectRation, zNear, zFar);
        return projectionMatrix;
    }

    public Matrix4f getWorldMatrix(Vector3f offset, Vector3f rotation, float scale) {
        worldMatrix.identity().translate(offset).
            rotateX((float)Math.toRadians(rotation.x)).
            rotateY((float)Math.toRadians(rotation.y)).
            rotateZ((float)Math.toRadians(rotation.z)).
            scale(scale);
        return worldMatrix;
    }

}
