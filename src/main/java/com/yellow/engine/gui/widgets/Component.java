package com.yellow.engine.gui.widgets;
import org.joml.Vector2f;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;

public abstract class Component {

    protected Vector2f position = new Vector2f();
    protected ImGuiWindowFlags[] windowFlags;
    protected String componentHash;

    public void begin(String componentHash) {
        this.componentHash = componentHash;

        ImGui.begin(componentHash);
    }

    public void begin(String componentHash, int[] windowFlags) {
        this.componentHash = componentHash;

        // Magia con bitwise OR per aggiungere la lista di flags
        int flags = 0;
        for(int i = 0; i < windowFlags.length; i++) {
            flags |= windowFlags[i];
        }

        ImGui.begin(componentHash, flags);
    }

    public void end() {
        ImGui.end();
    }

    // TODO: Il movimento non funziona come dovrebbe
    // Forse devi usare setCUrsorPos con ImGui
    // Move non continua  amuovere il widget ma lo lascia fermo lì
    public void move(float x, float y) {
        this.position.x += x;
        this.position.y += y;

        applyWindowPositionVector();
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;

        applyWindowPositionVector();
    }

    private void applyWindowPositionVector() {
        ImGui.setWindowPos(componentHash, position.x, position.y);
    }

}
