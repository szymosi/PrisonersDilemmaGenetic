package com.Biai.Game;

import java.util.Random;

/*
0-two cooperate
1-got betrtayed
2-betray
3-two betrays
 */
public class Prisoner {
    private int points = 0;
    private int tactic;
    Random r = new Random();

    boolean gotBetrayed =false;
    boolean previousEnemyMove=r.nextBoolean();

    public Prisoner(int tactic) {
        this.tactic = tactic;
    }
//TODO more tactics/player as prisoner
    public boolean play() {
        switch (tactic) {
            case 1:
                return r.nextBoolean();
            case 2:
                return true;
            case 3:
                return false;
            case 4:
                if(gotBetrayed)
                    return false;
                else
                    return true;
            case 5:
                return previousEnemyMove;
        }
        return true;
    }
    public void roundEnd(int result, int points) {
        if (result >= 0 && result < 4)
        {
            if(result==1||result==3) {
                gotBetrayed = true;
                previousEnemyMove=false;
            }
            else {
                previousEnemyMove=true;
            }
        }
        this.points += points;
    }

    public int getPoints() {
        return points;
    }
}
