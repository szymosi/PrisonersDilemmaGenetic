package com.Biai.Game;

import java.util.Random;

public class Prisoner {
    private int points = 0;
    private int tactic;
    Random r = new Random();

    public Prisoner(int tactic) {
        this.tactic = tactic;
    }

    public boolean play() {
        switch (tactic) {
            case 1:
                return r.nextBoolean();
        }
        return true;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }
}
