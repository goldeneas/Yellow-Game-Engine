package com.yellow.engine.handlers;

import java.util.ArrayList;
import java.util.List;

import com.yellow.engine.world.GameObject;

import org.joml.Vector3f;

// TODO: Forse devi usare un HashMap per conservare anche il tipo di oggetto nella lista
public class ObjectHandler {

    private static List<GameObject> gameObjects = new ArrayList<>();

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static GameObject get(int index){
        return gameObjects.get(index);
    }

    public static GameObject add(GameObject obj){
        gameObjects.add(obj);
        return obj;
    }

    public static GameObject add(GameObject obj, Vector3f position){
        obj.setPosition(position.x, position.y, position.z);
        return add(obj);
    }

    public static GameObject add(GameObject obj, Vector3f position, Vector3f rotation){
        obj.setRotation(rotation.x, rotation.y, rotation.z);
        return add(obj, position);
    }

    public static void remove(GameObject obj){
        gameObjects.remove(obj);
    }

    public static void clear(){
        gameObjects.clear();
    }
    
}
