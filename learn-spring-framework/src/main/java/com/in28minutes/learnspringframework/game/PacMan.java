package com.in28minutes.learnspringframework.game;

import com.in28minutes.learnspringframework.game.GamingConsole;

public class PacMan implements GamingConsole {
    @Override
    public void up() {
        System.out.println("Moving up to run");
    }

    @Override
    public void down() {
        System.out.println("dunk");
    }

    @Override
    public void left() {
        System.out.println("turning left");
    }

    @Override
    public void right() {
        System.out.println("turning right");
    }
}
