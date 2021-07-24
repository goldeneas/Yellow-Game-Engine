package com.yellow.engine.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int textureId;

    // Questo costruttore non va usato direttamente (se non in casi molto particolari).
    // Viene usato dal TextureHandler per creare una nuova texture SOLO SE non è già stata caricata dalla memoria.
    public Texture(int textureId) {
        this.textureId = textureId;
    }

    public int getTextureId(){
        return textureId;
    }

    public void dispose(){
        glDeleteTextures(textureId);
    }

}
