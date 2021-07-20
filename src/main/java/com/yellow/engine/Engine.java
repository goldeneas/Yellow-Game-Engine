package com.yellow.engine;

import com.yellow.engine.interfaces.IGame;
import com.yellow.engine.utils.Timer;
import com.yellow.engine.windows.Window;
import com.yellow.engine.windows.WindowOptions;

public class Engine implements Runnable {

    private IGame game;
    private Timer timer;
    private Window window;

    public Engine(IGame game, WindowOptions options) {
        this.game = game;
        this.timer = new Timer();
        this.window = new Window(options.width, options.height, options.title, options.clearColor);
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName("GAME_ENGINE_THREAD");

            init();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dispose();
        }
    }

    private void init() throws Exception {
        window.init();
        game.init(window);
    }

    private void dispose() {
        game.dispose();
    }

    private void loop() {
        double elapsedTime;
        double accumulator = 0f;
        double deltaTime = 1f / 60f;

        boolean running = true;
        while (running && !window.shouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= deltaTime) {
                update(deltaTime);
                accumulator -= deltaTime;
            }

            draw();
        }
    }

    private void input() {
        game.input(window);
    }

    private void draw() {
        window.draw();
        game.draw(window);
    }

    private void update(double deltaTime) {
        game.update(deltaTime);
    }

}
