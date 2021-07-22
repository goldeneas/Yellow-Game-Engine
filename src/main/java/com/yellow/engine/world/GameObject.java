package com.yellow.engine.world;

import com.yellow.engine.rendering.Mesh;

import org.joml.Vector3f;

public class GameObject {

    protected Mesh mesh;
    protected Vector3f position;
    protected Vector3f rotation;
    protected float scale;

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        this.mesh.generateBuffers(); // Generiamo il mesh (VAO e i VBOs)

        this.scale = 1;
        this.position = new Vector3f();
        this.rotation = new Vector3f();
    }

    protected GameObject() {
        // Genera un NPE dopo la draw call di questo oggetto se non è assegnato un mesh.
        // Questo costruttore è implementato solo da altri oggetti già definiti prima del runtime
        // Quindi se si vuole disegnare, c'è bisogno di un mesh.
        this.mesh = null;

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

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void move(float xAmount, float yAmount, float zAmount){
        this.position.x += xAmount;
        this.position.y += yAmount;
        this.position.z += zAmount;
    }

    public void move(float xAmount, float yAmount){
        this.move(xAmount, yAmount, 0);
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
