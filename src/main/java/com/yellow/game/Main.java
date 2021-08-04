package com.yellow.game;

import com.yellow.engine.Engine;
import com.yellow.engine.windows.WindowOptions;

public class Main {

    public static void main(String[] args) {
        try{
            TestGame game = new TestGame();
            //TestGui gui = new TestGui();
            //Engine engine = new Engine(game, gui, new WindowOptions(800, 800, "Yellow Engine"));
            Engine engine = new Engine(game, new WindowOptions(800, 800, "Yellow Engine"));
            engine.run();
        }catch (Exception excp){
            excp.printStackTrace();
            System.exit(-1);
        }
    }

}
