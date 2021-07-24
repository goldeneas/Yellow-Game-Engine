package com.yellow.engine.rendering;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.stackPush;

import java.util.HashMap;
import java.util.Map;

import com.yellow.engine.utils.Logger;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

// Questa classe è usata per rendere più facile l'unione di vertex e fragment shader.
// Per poter eseguire draw call alla GPU c'è bisogno anche di un Mesh.
public class ShaderProgram {

    private final int programId;
    private int vertexShaderId;
    private int fragmentShaderId;

    private Map<String, Integer> uniforms;

    public ShaderProgram() throws Exception {
        programId = glCreateProgram();
        if (programId == 0) {
            throw new Exception("Could not create Shader");
        }

        uniforms = new HashMap<>();
    }

    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
    }

    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;
    }

    public void link() throws Exception {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        if (vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId);
        }
        if (fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId);
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            Logger.error("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void dispose() {
        unbind();
        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }

    // L'uniform deve già avere un holder nella vertex shader, qui lo "binda".
    // Deve essere chiamato dopo ShaderProgram#link.
    public void initUniform(String uniformName) throws Exception{
        int uniformLocation = glGetUniformLocation(programId, uniformName);
        if(uniformLocation < 0) {
            throw new Exception("Could not find uniform: " + uniformLocation);
        }

        uniforms.put(uniformName, uniformLocation);
    }

    // Questo metodo va chiamato dopo che ShaderProgram#bind è stato invocato.
    public void setUniformValue(String uniformName, Matrix4f value){
        try(MemoryStack stack = stackPush()) {
            glUniformMatrix4fv(uniforms.get(uniformName), false,
                               value.get(stack.mallocFloat(16)));
        }
    }

    public void setUniformValue(String uniformName, int value){
        glUniform1f(uniforms.get(uniformName), value);
    }

    public int getProgramId(){
        return programId;
    }
}