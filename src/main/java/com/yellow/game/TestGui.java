package com.yellow.game;

import com.yellow.engine.interfaces.IGuiLayer;

import imgui.ImGui;

public class TestGui implements IGuiLayer{

    private boolean showText = false;

    @Override
    public void draw() {
        ImGui.begin("Hi");
        if (ImGui.button("I am a button")) {
            showText = true;
        }

        if (showText) {
            ImGui.text("You clicked a button");
            ImGui.sameLine();
            if (ImGui.button("Stop showing text")) {
                showText = false;
            }
        }
        ImGui.end();
    }
    
}
