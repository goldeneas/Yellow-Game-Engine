package com.yellow.engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import com.yellow.engine.utils.Color;
import com.yellow.engine.utils.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	// The window handle
	private long window;

    private int width, height;
    private String title;
    private Color clearColor;

	// Variabili usate per la posizione del mouse quando viene cliccata la finestra
	// Vedi linea 124 in #draw()
	private double firstMouseX, firstMouseY = 0;

	// Buffer usati ad esempio per salvare la posizione della finestra
	private IntBuffer tmpIntBuffer1 = BufferUtils.createIntBuffer(1);
	private IntBuffer tmpIntBuffer2 = BufferUtils.createIntBuffer(1);

	private DoubleBuffer tmpDoubleBuffer1 = BufferUtils.createDoubleBuffer(1);
	private DoubleBuffer tmpDoubleBuffer2 = BufferUtils.createDoubleBuffer(1);

	public Window(int width, int height, String title, Color clearColor) {
		this.width = width;
        this.height = height;
        this.title = title;
		this.clearColor = clearColor;
	}

	public void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
		glfwWindowHint(GLFW_DECORATED, GLFW_FALSE); // the window will have the default decorators disabled

		// Create the window
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_PRESS ){
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Set the clear color
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
	}

    public void draw() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		
		glfwSwapBuffers(window); // swap the color buffers
        
        // Poll for window events. The key callback above will only be
        // invoked during this call.
		glfwPollEvents();

		// Window-dragging with mouse behaviour (to be used when decorators are disabled)
		if (isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            if (firstMouseX == 0 && firstMouseY == 0) {
                firstMouseX = getMousePosition().x;
                firstMouseY = getMousePosition().y;
            } else {
                int dx, dy; // delta x, delta y
                dx = (int) (firstMouseX - getMousePosition().x);
                dy = (int) (firstMouseY - getMousePosition().y);

                moveWindow(dx, dy);
            }
        } else if (isMouseButtonReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            if (firstMouseX != 0 && firstMouseY != 0) { firstMouseX = 0; firstMouseY = 0; }
        }
    }

	public void moveWindow(int x, int y){
		setWindowPosition((int) (getWindowPosition().x - x), (int) (getWindowPosition().y - y));
	}

	public void setWindowPosition(int x, int y){
		glfwSetWindowPos(window, x, y);
	}

	public Vector getWindowPosition(){
		glfwGetWindowPos(window, tmpIntBuffer1, tmpIntBuffer2);
		return new Vector(tmpIntBuffer1.get(0), tmpIntBuffer2.get(0));
	}

	public Vector getMousePosition(){
		glfwGetCursorPos(window, tmpDoubleBuffer1, tmpDoubleBuffer2);
		return new Vector(tmpDoubleBuffer1.get(0), tmpDoubleBuffer2.get(0));
	}

    public void setClearColor(Color newColor){
        glClearColor(newColor.r, newColor.g, newColor.b, newColor.a);
    }

	public boolean isKeyDown(int key){
		return glfwGetKey(window, key) == GLFW_PRESS;
	}

	public boolean isMouseButtonDown(int button){
		return glfwGetMouseButton(window, button) == GLFW_PRESS;
	}

	public boolean isMouseButtonReleased(int button){
		return glfwGetMouseButton(window, button) == GLFW_RELEASE;
	}

    public boolean shouldClose(){
        return glfwWindowShouldClose(window);
    }

	public long getHandle(){
		return window;
	}
}