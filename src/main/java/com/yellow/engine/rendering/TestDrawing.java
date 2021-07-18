package com.yellow.engine.rendering;

import static org.lwjgl.opengl.GL33.*;

import com.yellow.engine.utils.Logger;

public class TestDrawing {

    private final float[] vertices = { 
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f,
        0.0f,  0.5f, 0.0f
    };

    private int vao, vbo, program;

    private final String vertexShaderSource = "#version 330 core\n layout (location = 0) in vec3 aPos;\n void main()\n {\n gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n }\0";
    private final String fragmentShaderSource = "#version 330 core\n out vec4 FragColor;\n void main()\n {\n FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);\n }\n";

    public TestDrawing() {
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, vertices.length);

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderSource);

        glCompileShader(vertexShader);
        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == 0) {
            Logger.error("Error compiling Shader code: " + glGetShaderInfoLog(vertexShader, 1024));
        }

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderSource);

        glCompileShader(fragmentShader);
        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == 0) {
            Logger.error("Error compiling Shader code: " + glGetShaderInfoLog(fragmentShader, 1024));
        }

        int program = glCreateProgram();
        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);
        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
            Logger.error("Error linking Shader code: " + glGetProgramInfoLog(program, 1024));
        }

        glValidateProgram(program);
        if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
            Logger.error("Warning validating Shader code: " + glGetProgramInfoLog(program, 1024));
        }

        glUseProgram(program);
        
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    }

    public void draw(){
        glUseProgram(program);
        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

}
