package com.yellow.game;

import com.yellow.engine.Engine;
import com.yellow.engine.windows.WindowOptions;

public class Main {

    public static void main(String[] args) {
        try{
            TestGame game = new TestGame();
            Engine engine = new Engine(game, new WindowOptions(520, 480, "Title"));
            engine.run();
        }catch (Exception excp){
            excp.printStackTrace();
            System.exit(-1);
        }
    }

}