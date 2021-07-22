package com.yellow.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.stb.STBImage.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Paths;

import com.yellow.engine.utils.Logger;

import org.lwjgl.system.MemoryStack;

public class Texture {

    private String imageName;
    private int textureId;

    public Texture(String imageName){
        this.imageName = imageName;
    }

    // Possiamo anche definire l'immagine della texture in seguito
    // chiamando loadTexture con la stringa dell'immagine stessa.
    public Texture() {}

    public int loadImage(String imageName) throws Exception {
        int width, height;
        ByteBuffer byteBuffer;
        try(MemoryStack stack = stackPush()) {
            IntBuffer wBuffer = stack.callocInt(1);
            IntBuffer hBuffer = stack.callocInt(1);
            IntBuffer channels = stack.callocInt(1);

            // Carica l'immagine nel byteBuffer.
            // In pratica serve a prendere la texture in byte, la sua larghezza e la sua altezza
            // che sono poi messi nei buffer, che sono trasferiti nelle rispettive variabili.
            byteBuffer = stbi_load(Paths.get(imageName).toString(), wBuffer, hBuffer, channels, 4);
            if (byteBuffer == null) {
                throw new Exception("Image file [" + imageName  + "] not loaded: " + stbi_failure_reason());
            }

            byteBuffer.flip();

            width = wBuffer.get(0);
            height = hBuffer.get(0);
        }

        // Solita storia, vedi Mesh.java per capire.
        int textureId = glGenTextures();
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

    public void loadImage() throws Exception{
        this.loadImage(imageName);
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public int getTextureId(){
        return textureId;
    }

    public void dispose(){
        glDeleteTextures(textureId);
    }

}
