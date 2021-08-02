package com.yellow.game;

import com.yellow.engine.gui.widgets.Dialogue;
import com.yellow.engine.gui.widgets.InputField;
import com.yellow.engine.interfaces.IGuiLayer;

public class TestGui implements IGuiLayer{

    private Dialogue testDialogue;
    private InputField testField;

    @Override
    public void init() {
        testDialogue = new Dialogue();
        testField = new InputField();
    }

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
