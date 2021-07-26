package com.yellow.engine.world;

import java.util.ArrayList;

import com.yellow.engine.handlers.ObjectHandler;
import com.yellow.engine.prefabs.base.Block;

import org.joml.Vector3f;

public class Chunk {

    private final int CHUNK_SIZE = 16;

    private Vector3f chunkPosition;
    private ArrayList<Block> chunkBlocks;
    private ArrayList<Block> chunkActiveBlocks;

    public Chunk(Vector3f position) {
        chunkPosition = position;

        chunkBlocks = new ArrayList<>();
        chunkActiveBlocks = new ArrayList<>();

        this.generateChunkBlocks();
        this.reloadChunkActiveBlocks();
    }

    private void generateChunkBlocks() {
        for(int x = 0; x < CHUNK_SIZE; x++) {
            for(int y = 0; y < CHUNK_SIZE; y++) {
                for(int z = 0; z < CHUNK_SIZE; z++) {
                    Block currentBlock = new Block();
                    Vector3f currentPos = new Vector3f(x, y, z);

                    ObjectHandler.add(currentBlock, currentPos);
                    chunkBlocks.add(currentBlock);
                }
            }
        }
    }

    private void reloadChunkActiveBlocks() {
        // TODO: Cambia questa logica, controlla se i blocchi confinano con aria
        // se lo fanno, sono attivi perchè visibili.
        for(Block block : chunkBlocks) {
            if(block.getPosition().x == 0 || block.getPosition().y == 0 || block.getPosition().z == 0) {
                chunkActiveBlocks.add(block);
                continue;
            }
            if(block.getPosition().x == CHUNK_SIZE || block.getPosition().y == CHUNK_SIZE || block.getPosition().z == CHUNK_SIZE) {
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
