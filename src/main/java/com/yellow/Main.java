package com.yellow;

import com.yellow.engine.Engine;

public class Main{

    public static void main(String[] args){
        TestGame game = new TestGame();
        Engine engine = new Engine(game);
        engine.run();
    }

}
