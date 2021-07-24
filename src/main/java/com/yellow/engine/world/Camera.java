package com.yellow.engine.world;

import org.joml.Vector3f;

public class Camera {

    private Vector3f position, rotation;

    public Camera() {
        this.position = new Vector3f();
        this.rotation = new Vector3f();
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void move(float offsetX, float offsetY, float offsetZ){
        // Seno e coseno sono usati per far muovere la telecamera in base a dove punta
        if ( offsetZ != 0 ) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
            position.z += (float)Math.cos(Math.toRadians(rotation.y)) * offsetZ;
        }
        if ( offsetX != 0) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX;
            position.z += (float)Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;
        }
        position.y += offsetY;
    }

    public void move(float offsetX, float offsetY) {
        this.move(offsetX, offsetY, 0);
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void rotate(float offsetX, float offsetY, float offsetZ){
        this.rotation.x += offsetX;
        this.rotation.y += offsetY;
        this.rotation.z += offsetZ;
    }

    public void rotate(float offsetX, float offsetY){
        this.rotate(offsetX, offsetY, 0);
    }

    public void setRotation(float x, float y, float z){
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

}
