package com.yellow.engine.handlers;

import java.util.ArrayList;

import com.yellow.engine.world.Chunk;

import org.joml.Vector3f;

public class ChunkHandler {

    private static ArrayList<Chunk> chunks = new ArrayList<>();

    public static void generateChunk(Vector3f position) {
        Chunk chunk = new Chunk(position);
        chunks.add(chunk);
    }

    public static ArrayList<Chunk> getChunks() {
        return chunks;
    }
    
}
