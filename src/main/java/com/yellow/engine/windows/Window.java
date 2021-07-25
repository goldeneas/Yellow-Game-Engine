package com.yellow.engine.windows;

import org.joml.Vector2d;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import com.yellow.engine.utils.Color;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	// The window handle
	private long windowHandle;

	private int width, height;
	private String title;
	private Color clearColor;

	// Variabili usate per la posizione del mouse quando viene cliccata la finestra
	// Vedi linea 124 in #draw()
	private double firstMouseX, firstMouseY = 0;

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
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
		glfwWindowHint(GLFW_DECORATED, GLFW_FALSE); // the window will have the default decorators disabled

		// Create the window
		windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
		if (windowHandle == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated
		// or released.
		glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
			}
		});

		// Get the thread stack and push a new frame
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(windowHandle, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(windowHandle, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(windowHandle);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(windowHandle);

		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Set the clear color
		glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);

		// Draw pixel further away before closer ones
		glEnable(GL_DEPTH_TEST);

		// Enable face culling
		glEnable(GL_CULL_FACE);
	}

	public void draw() {
		glfwSwapBuffers(windowHandle); // swap the color buffers

		// Poll for window events. The key callback above will only be
		// invoked during this call.
		glfwPollEvents();

		// Window-dragging with mouse behaviour.
		// (to be used when decorators are disabled).
		if (isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			if (firstMouseX == 0 && firstMouseY == 0) {
				firstMouseX = getMousePosition().x;
				firstMouseY = getMousePosition().y;
			} else {
				int dx, dy; // delta x, delta y
				dx = (int) (firstMouseX - getMousePosition().x);
				dy = (int) (firstMouseY - getMousePosition().y);

				moveWindow(dx, dy);
			}
		} else if (isMouseButtonReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			if (firstMouseX != 0 && firstMouseY != 0) {
				firstMouseX = 0;
				firstMouseY = 0;
			}
		}
	}

	public void clearWindow(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	}

	public void moveWindow(int x, int y) {
		setWindowPosition((int) (getWindowPosition().x - x), (int) (getWindowPosition().y - y));
	}

	public void setWindowPosition(int x, int y) {
		glfwSetWindowPos(windowHandle, x, y);
	}

	public Vector2d getWindowPosition() {
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.callocInt(1);
			IntBuffer pHeight = stack.callocInt(1);

			glfwGetWindowPos(windowHandle, pWidth, pHeight);
			return new Vector2d(pWidth.get(0), pHeight.get(0));
		}
	}

	public Vector2d getMousePosition() {
		try (MemoryStack stack = stackPush()) {
			DoubleBuffer pX = stack.callocDouble(1);
			DoubleBuffer pY = stack.callocDouble(1);

			glfwGetCursorPos(windowHandle, pX, pY);
			return new Vector2d(pX.get(0), pY.get(0));
		}
	}

	public long getWindowHandle() {
		return windowHandle;
	}

	public int getWindowWidth(){
		return width;
	}

	public int getWindowHeight(){
		return height;
	}

	public void setClearColor(Color newColor) {
		glClearColor(newColor.r, newColor.g, newColor.b, newColor.a);
	}

	public boolean isKeyDown(int key) {
		return glfwGetKey(windowHandle, key) == GLFW_PRESS;
	}

	public boolean isMouseButtonDown(int button) {
		return glfwGetMouseButton(windowHandle, button) == GLFW_PRESS;
	}

	public boolean isMouseButtonReleased(int button) {
		return glfwGetMouseButton(windowHandle, button) == GLFW_RELEASE;
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(windowHandle);
	}
}