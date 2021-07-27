package com.yellow.engine.windows;

import com.yellow.engine.interfaces.IGuiLayer;

import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class ImGuiLayer {

    private ImGuiImplGl3 imGuiGl3;
    private ImGuiImplGlfw imGuiGlfw;
    private IGuiLayer customGuiLayer;

    public void init(long windowHandle, IGuiLayer customGuiLayer) {
        imGuiGl3 = new ImGuiImplGl3();
        imGuiGlfw = new ImGuiImplGlfw();
        this.customGuiLayer = customGuiLayer; 

        ImGui.createContext();
        // ImGuiIO io = ImGui.getIO();
        // io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init();
    }

    public void draw() {
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        customGuiLayer.draw();

        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());

        // if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
        //     final long backupWindowPtr = org.lwjgl.glfw.GLFW.glfwGetCurrentContext();
        //     ImGui.updatePlatformWindows();
        //     ImGui.renderPlatformWindowsDefault();
        //     GLFW.glfwMakeContextCurrent(backupWindowPtr);
        // }
    }

    public void dispose() {
        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();
    }
    
}
