package com.yellow.engine.world;

import java.util.ArrayList;

import com.yellow.engine.handlers.ObjectHandler;
import com.yellow.engine.prefabs.base.Block;

import org.joml.Vector3f;

public class Chunk {

    private final int CHUNK_SIZE = 16;

    // TODO: Problema di indexing: due chunk in (0, 0, 0) e (1, 0, 0) o hanno la stessa posizione oppure quello con 0,0,0 non esiste (se moltiplichiamo per la posizione dei cubi).
    // Non so che soluzione applicare :)
    private Vector3f chunkPosition;

    private ArrayList<Block> chunkBlocks;
    private ArrayList<Block> chunkActiveBlocks;

    // Bisogna impostare la posizione prima di crearlo
    public Chunk() {
        chunkBlocks = new ArrayList<>();
        chunkActiveBlocks = new ArrayList<>();
    }
    
    // Genera il chunk in quella posizione
    public void generateChunkBlocks() {
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
            if(block.getPosition().x == 0 || block.getPosition().y == 0 || block.getPosition().z == 0) {
                chunkActiveBlocks.add(block);
                // TODO: Quando refreshi un chunk devi anche rimuovere i blocchi che c'erano prima nell'object handler.
                // sarebbe una buona idea creare un chunk handler.
                ObjectHandler.add(block, block.getPosition()); //TODO NON FUNZIONA PER COLPA DI QUESTO QUI CH ENON POSSO CMABIARE IN TEMPO DEVO ANDAR CIAO
                continue;
            }

            // Togliamo uno dal chunk size per l'indexing (che inizia da 0).
            // E poi togliamo CHUNKSIZE * chunkPos per prendere la posizione del blocco come se fosse in un chunk a (0, 0, 0).
            if(block.getPosition().x - (CHUNK_SIZE * chunkPosition.x) == (CHUNK_SIZE-1) 
            || block.getPosition().y - (CHUNK_SIZE * chunkPosition.y) == (CHUNK_SIZE-1)
            || block.getPosition().z - (CHUNK_SIZE * chunkPosition.z) == (CHUNK_SIZE-1)) {
                chunkActiveBlocks.add(block);
                ObjectHandler.add(block, block.getPosition());
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

    public void setPosition(float x, float y, float z) {
        this.chunkPosition = new Vector3f(x, y, z);
    }
    
}
