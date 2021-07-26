package com.yellow.engine.handlers;

import java.util.ArrayList;

import com.yellow.engine.world.Chunk;

import org.joml.Vector3f;

public class ChunkHandler {

    // TODO: Crea un chunk renderer probabilmente multithreaded

    private static ArrayList<Chunk> chunks = new ArrayList<>();

    public static void add(Chunk chunk, Vector3f position) {
        chunk.setPosition(position.x, position.y, position.z);
        chunks.add(chunk);
    }

    public static ArrayList<Chunk> getChunks() {
        return chunks;
    }
    
}
