package com.yellow.engine.windows.components;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;

public class Dialogue extends Component{
    private String text = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello \nASDADASDADAD";
    private int index = 0;

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
        ImGui.text(text.substring(0, index));

        index++;
        if(index > text.length()) {
            index = text.length();
        }
    }
    
}
