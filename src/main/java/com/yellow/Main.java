package com.yellow;

import com.yellow.engine.Engine;
import com.yellow.engine.utils.WindowOptions;

public class Main{

    public static void main(String[] args){
        TestGame game = new TestGame();
        Engine engine = new Engine(game, new WindowOptions(520, 480, "Title"));
        engine.run();
    }

}
