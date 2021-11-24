package com.geekbrains.part2.lesson1;

import java.util.Random;

public class Obstacle {
    private int difficulty;

    public Obstacle() {
        this.difficulty = generateBarrier();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void goChallenge(Member member) {
        member.jump(getDifficulty());
    }

    public static int generateBarrier() {
        Random random =  new Random();
        return random.nextInt(10);
    }
}