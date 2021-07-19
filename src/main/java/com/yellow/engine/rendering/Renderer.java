package com.yellow.engine.rendering;

import com.yellow.engine.utils.Utils;
import com.yellow.engine.windows.Window;

import static org.lwjgl.opengl.GL33.*;
public class Renderer {

    private ShaderProgram shader;

    public void init(String vertexPath, String fragmentPath) throws Exception{
        shader = new ShaderProgram();
        shader.createVertexShader(Utils.loadResource(vertexPath));
        shader.createFragmentShader(Utils.loadResource(fragmentPath));
        shader.link();
    }

    public void draw(Mesh mesh, Window window){
        window.clearWindow(); // Clear the framebuffer
        mesh.generateMesh();

        shader.bind();

        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);

        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

        shader.unbind();
    }

    public void dispose(){
        if(shader != null) { shader.dispose(); }
    }
    
}
