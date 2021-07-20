package com.yellow.engine.world;

import com.yellow.engine.rendering.Mesh;

import org.joml.Vector3f;

public class GameObject {

    private Mesh mesh;
    private Vector3f position;
    private Vector3f rotation;
    private float scale;

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        this.mesh.generateMesh(); // Generiamo il mesh (VAO e i VBOs)

        this.scale = 1;
        this.position = new Vector3f();
        this.rotation = new Vector3f();
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setMesh(Mesh mesh){
        if(this.mesh != null) { this.mesh.dispose(); }
        this.mesh = mesh;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void setRotation(float x, float y, float z){
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public void setScale(float scale){
        this.scale = scale;
    }

}
