package com.yellow.engine.handlers;

import java.util.ArrayList;
import java.util.List;

import com.yellow.engine.world.GameObject;

// TODO: Pensa se sia una buona classe, ben ottimizzata con list o se c'è di meglio.
// Pensa anche se questa classe ha senso o se altri giochi usano un metodo diverso per
// iterare fra gli oggetti.
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

    public static void remove(GameObject obj){
        gameObjects.remove(obj);
    }

    public static void clear(){
        gameObjects.clear();
    }
    
}
