package com.yellow.engine.windows.components;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImString;

public class InputField extends Component{
    
    private ImString text;

    public InputField() {
        this.text = new ImString();
    }

    @Override
    public void begin(String componentHash) {
        int[] windowFlags = {
            ImGuiWindowFlags.AlwaysAutoResize,
            ImGuiWindowFlags.NoCollapse,
            ImGuiWindowFlags.NoDecoration
        };
        super.begin(componentHash, windowFlags);
    }

    public void draw() {
        ImGui.inputTextMultiline(componentHash, text);
    }

}
