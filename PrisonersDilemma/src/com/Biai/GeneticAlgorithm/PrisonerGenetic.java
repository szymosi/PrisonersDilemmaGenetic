package com.Biai.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrisonerGenetic {

    /*
    0-two cooperate
    1-got betrtayed
    2-betray
    3-two betrays
     */
    private List<Integer> memory = new ArrayList();
    private Boolean[][][][][] knowledge = new Boolean[4][4][4][4][4];
    private int points = 0;


    public void randomFill() {
        Random r = new Random();
        for (int a = 0; a < 4; a++)
            for (int b = 0; b < 4; b++)
                for (int c = 0; c < 4; c++)
                    for (int d = 0; d < 4; d++)
                        for (int e = 0; e < 4; e++)
                            knowledge[a][b][c][d][e] = r.nextBoolean();
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public boolean play() {
        if (memory.size() >= 5)
            return knowledge[memory.get(0)][memory.get(1)][memory.get(2)][memory.get(3)][memory.get(4)];
        else
            return true;
    }

    public void roundEnd(int result, int points) {
        if (result >= 0 && result < 4)
            memory.add(0, result);
        this.points += points;
    }
    public void mutate(){//TODO

    }
}
