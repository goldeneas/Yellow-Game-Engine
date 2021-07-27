package com.yellow.engine.world;

import java.util.ArrayList;

import com.yellow.engine.prefabs.base.Block;

import org.joml.Vector3f;

public class Chunk {

    private final int CHUNK_SIZE = 16;

    private Vector3f chunkPosition;

    private ArrayList<Block> chunkBlocks;
    private ArrayList<Block> chunkActiveBlocks;

    // Usato da ChunkHandler, normalmente non va chiamato da solo.
    public Chunk(Vector3f chunkPosition) {
        chunkBlocks = new ArrayList<>();
        chunkActiveBlocks = new ArrayList<>();

        this.chunkPosition = chunkPosition;

        this.generateChunkBlocks();
    }
    
    // Genera il chunk in quella posizione
    private void generateChunkBlocks() {
        chunkBlocks.clear();

        for(int x = 0; x < CHUNK_SIZE; x++) {
            for(int y = 0; y < CHUNK_SIZE; y++) {
                for(int z = 0; z < CHUNK_SIZE; z++) {
                    Block currentBlock = new Block();
                    Vector3f currentPos = new Vector3f(x, y, z);

                    currentBlock.setPosition(
                        currentPos.x + (CHUNK_SIZE * chunkPosition.x),
                        currentPos.y + (CHUNK_SIZE * chunkPosition.y),
                        currentPos.z + (CHUNK_SIZE * chunkPosition.z));
                    chunkBlocks.add(currentBlock);
                }
            }
        }

        this.reloadChunkActiveBlocks();
    }

    public void reloadChunkActiveBlocks() {
        // TODO: Cambia questa logica, controlla se i blocchi confinano con aria
        // se lo fanno, sono attivi perchè visibili.
        for(Block block : chunkBlocks) {
            if(block.getPosition().x - (CHUNK_SIZE * chunkPosition.x) == 0
            || block.getPosition().y - (CHUNK_SIZE * chunkPosition.y) == 0
            || block.getPosition().z - (CHUNK_SIZE * chunkPosition.z) == 0) {
                chunkActiveBlocks.add(block);
                continue;
            }

            // Togliamo uno dal chunk size per l'indexing (che inizia da 0).
            if(block.getPosition().x - (CHUNK_SIZE * chunkPosition.x) == (CHUNK_SIZE-1) 
            || block.getPosition().y - (CHUNK_SIZE * chunkPosition.y) == (CHUNK_SIZE-1)
            || block.getPosition().z - (CHUNK_SIZE * chunkPosition.z) == (CHUNK_SIZE-1)) {
                chunkActiveBlocks.add(block);
                continue;
            }
        }
    }

    public Block getBlockAt(float x, float y, float z) {
        for(Block block : chunkBlocks) {
            if(block.getPosition() == new Vector3f(x, y, z)) {
                return block;
            }
        }

        return null;
    }

    public ArrayList<Block> getChunkBlocks() {
        return chunkBlocks;
    }

    public ArrayList<Block> getChunkActiveBlocks() {
        return chunkActiveBlocks;
    }
    
}
