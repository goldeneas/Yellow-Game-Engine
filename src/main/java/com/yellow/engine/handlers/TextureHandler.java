package com.yellow.engine.handlers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.stb.STBImage.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.yellow.engine.rendering.Texture;

import org.lwjgl.system.MemoryStack;

public class TextureHandler {

    private static String resourcesPath = Path.of("").toAbsolutePath().toString() + "\\src\\main\\resources\\textures\\";
    private static Map<String, Texture> textureMap = new HashMap<>();

    // Il nome del .png nella cartella resources/texture sarà anche il nome della texture
    // sarà assegnata al blocco.
    // imageName deve anche avere l'estensione del file (ex. grassblock.png).

    // Ex. TextureHandler.getTexture("grassblock.png");
    public static Texture getTexture(String imageName) {
        // Se è già stata caricata la texture, la ritorniamo e risparmiamo il tempo
        // che si impiegherebbe a ricaricarla dalla cartella
        if(textureMap.containsKey(imageName)) {
            return textureMap.get(imageName);
        }else{
            Texture texture = new Texture(getTextureIdLoadingImage(imageName));
            textureMap.put(imageName, texture);
            return texture;
        }
    }

    private static int getTextureIdLoadingImage(String imageName) {
        int width, height;
        ByteBuffer byteBuffer;
        try(MemoryStack stack = stackPush()) {
            IntBuffer wBuffer = stack.callocInt(1);
            IntBuffer hBuffer = stack.callocInt(1);
            IntBuffer channels = stack.callocInt(1);

            // Carica l'immagine nel byteBuffer.
            // In pratica serve a prendere la texture in byte, la sua larghezza e la sua altezza
            // che sono poi messi nei buffer, che sono trasferiti nelle rispettive variabili.
            byteBuffer = stbi_load(resourcesPath + imageName, wBuffer, hBuffer, channels, 4);
            if (byteBuffer == null) {
                throw new IllegalStateException("Image file [" + imageName  + "] not loaded: " + stbi_failure_reason());
            }

            byteBuffer.flip();

            width = wBuffer.get(0);
            height = hBuffer.get(0);
        }

        // Solita storia, vedi Mesh.java per capire.
        int textureId = glGenTextures();

        // TODO: Qui bindiamo anche la texture, però forse dobbiamo cambiarlo per usare diverse texture per diversi blocchi
        // Non so ancora bene come funziona.
        glBindTexture(GL_TEXTURE_2D, textureId);

        // Spiega ad OpenGL come estrarre i byte RGBA
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, byteBuffer);

        // Genera la mipmap della texture
        // (Una texture con risoluzione minore)
        glGenerateMipmap(GL_TEXTURE_2D);

        // Svuotiamo la memoria
        stbi_image_free(byteBuffer);

        return textureId;
    }
    
}
