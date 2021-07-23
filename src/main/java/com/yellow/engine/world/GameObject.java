package com.yellow.engine.world;

import com.yellow.engine.handlers.ObjectHandler;
import com.yellow.engine.rendering.Mesh;

import org.joml.Vector3f;

public class GameObject {

    protected Mesh mesh;
    protected Vector3f position;
    protected Vector3f rotation;
    protected float scale;

    //private final float GAMEOBJECT_MOVEMENT_STEP = 0.1f;

    // Usato per creare un gameObject non prefab.
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

    protected GameObject(Vector3f position) {
        this();
        this.position = position;
    }

    protected GameObject(Vector3f position, Vector3f rotation) {
        this(position);
        this.rotation = rotation;
    }

    // Questo metodo è una scorciatoia per GameObject#getMesh#draw().
    public void draw(){
        this.mesh.draw();
    }

    // Questo metodo è una scorciatoia per GameObject#getMesh#dispose()
    // e rimuove anche l'oggetto dalla lista dei gameobject.
    public void dispose(){
        ObjectHandler.remove(this);
        this.mesh.dispose();
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

    public void move(float offsetX, float offsetY, float offsetZ){
        this.position.x += offsetX;
        this.position.y += offsetY;
        this.position.z += offsetZ;
    }

    public void move(float offsetX, float offsetY){
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

    public void setScale(float scale){
        this.scale = scale;
    }

}
