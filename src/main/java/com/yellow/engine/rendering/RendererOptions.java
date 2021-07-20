package com.yellow.engine.rendering;

public class RendererOptions {

    public float FOV;
    public float Z_NEAR, Z_FAR;

    public RendererOptions(float FOV, float Z_NEAR, float Z_FAR){
        this.FOV = FOV;
        this.Z_FAR = Z_FAR;
        this.Z_NEAR = Z_NEAR;
    }
    
}
