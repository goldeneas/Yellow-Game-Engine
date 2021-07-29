package com.yellow.game;

import com.yellow.engine.interfaces.IGuiLayer;
import com.yellow.engine.windows.components.Dialogue;
import com.yellow.engine.windows.components.InputField;

public class TestGui implements IGuiLayer{

    private Dialogue testDialogue;
    private InputField testField;

    @Override
    public void init() {
        testDialogue = new Dialogue();
        testField = new InputField();
    }

    // TODO: Metti invece del draw un update così lo sincronizziamo con gli fps del gioco
    @Override
    public void draw() {
        // testDialogue.begin("ComponentHash1");
        // testDialogue.setPosition(150, 150);
        // testDialogue.draw();
        // testDialogue.end();

        // testField.begin("##TestField");
        // testField.draw();
        // testField.end();
    }
    
}
