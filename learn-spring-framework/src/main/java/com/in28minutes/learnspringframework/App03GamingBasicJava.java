package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GamingConsole;
import com.in28minutes.learnspringframework.game.MarioGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingBasicJava {

    public static void main(String[] args){

//        MarioGame game = new MarioGame(); //We can even declare var game = new MarioGame(), where var is a type reference, that detects the type of(continuation in next line)
//        GameRunner gameRunner = new GameRunner(game); //the variable declared from its surroundings, used only when declaring local variables
//        var game = new SuperContra();
//        GameRunner gameRunner = new GameRunner(game);
//        var game = new PacMan();
//        GameRunner gameRunner = new GameRunner(game);  //In this step we are creating objects and wiring the dependencies //Game is a Dependency of GameRunner
//        gameRunner.run();

        try(var context = new AnnotationConfigApplicationContext(GameConfiguration.class)){
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }
    }
}

