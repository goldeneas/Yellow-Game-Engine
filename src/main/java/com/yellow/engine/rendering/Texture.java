package com.yellow.engine.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int textureId;

    // Questo costruttore non va usato direttamente (se non in casi molto particolari).
    // Viene usato dal TextureHandler per creare una nuova texture SOLO SE non è già stata caricata dalla memoria.
    public Texture(int textureId) {
        this.textureId = textureId;
    }

    // public int loadImage(String imagePath) throws Exception {
    //     int width, height;
    //     ByteBuffer byteBuffer;
    //     try(MemoryStack stack = stackPush()) {
    //         IntBuffer wBuffer = stack.callocInt(1);
    //         IntBuffer hBuffer = stack.callocInt(1);
    //         IntBuffer channels = stack.callocInt(1);

    //         // Carica l'immagine nel byteBuffer.
    //         // In pratica serve a prendere la texture in byte, la sua larghezza e la sua altezza
    //         // che sono poi messi nei buffer, che sono trasferiti nelle rispettive variabili.
    //         byteBuffer = stbi_load(resourcesPath + imagePath, wBuffer, hBuffer, channels, 4);
    //         if (byteBuffer == null) {
    //             throw new Exception("Image file [" + imagePath  + "] not loaded: " + stbi_failure_reason());
    //         }

    //         byteBuffer.flip();

    //         width = wBuffer.get(0);
    //         height = hBuffer.get(0);
    //     }

    //     // Solita storia, vedi Mesh.java per capire.
    //     int textureId = glGenTextures();
    //     glBindTexture(GL_TEXTURE_2D, textureId);

    //     // Spiega ad OpenGL come estrarre i byte RGBA
    //     glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

    //     glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, byteBuffer);

    //     // Genera la mipmap della texture
    //     // (Una texture con risoluzione minore)
    //     glGenerateMipmap(GL_TEXTURE_2D);

    //     // Svuotiamo la memoria
    //     stbi_image_free(byteBuffer);

    //     // Non serve un return per forza, possiamo anche usare getTextureId al suo posto.
    //     // (Così si evita di creare una variabile in alcune situazioni).
    //     return textureId;
    // }

    // public void loadImage() throws Exception{
    //     this.loadImage(imagePath);
    // }

    // public void bind(){
    //     glBindTexture(GL_TEXTURE_2D, textureId);
    // }

    public int getTextureId(){
        return textureId;
    }

    public void dispose(){
        glDeleteTextures(textureId);
    }

}
