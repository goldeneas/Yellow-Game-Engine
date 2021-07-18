package com.yellow.engine.rendering;

import com.yellow.engine.utils.Color;

public class WindowOptions {
    public int width = 300, height = 300;
    public String title = "Hello World";
    public Color clearColor = new Color(37f, 38f, 39f, 255f);

    public WindowOptions(int width, int height, String title, Color clearColor) {
        this(width, height, title);
        this.clearColor = clearColor;
    }

    public WindowOptions(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }
}
