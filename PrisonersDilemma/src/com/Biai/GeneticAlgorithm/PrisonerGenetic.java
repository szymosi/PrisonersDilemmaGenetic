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

    public PrisonerGenetic(){randomFill();}
    public PrisonerGenetic(Boolean[][][][][] knowledge){this.knowledge=knowledge;}

    private void randomFill() {
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

    public Boolean[][][][][] getKnowledge(){return knowledge;};

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

    public void mutate(int numberOfMutations){
        Random r=new Random();
        for (int i=0;i<numberOfMutations;i++)
        {
            int a=r.nextInt(4);
            int b=r.nextInt(4);
            int c=r.nextInt(4);
            int d=r.nextInt(4);
            int e=r.nextInt(4);
            knowledge[a][b][c][d][e]=!knowledge[a][b][c][d][e];
        }
    }
    //TODO iffrent corssover mechanics
    public PrisonerGenetic crossover(PrisonerGenetic prisonerGenetic)
    {
        Boolean[][][][][] knowledge = new Boolean[4][4][4][4][4];
        Random r = new Random();
        for (int a = 0; a < 4; a++)
            for (int b = 0; b < 4; b++)
                for (int c = 0; c < 4; c++)
                    for (int d = 0; d < 4; d++)
                        for (int e = 0; e < 4; e++)
                            if(r.nextBoolean())
                                knowledge[a][b][c][d][e]=this.knowledge[a][b][c][d][e];
                            else
                                knowledge[a][b][c][d][e]=prisonerGenetic.getKnowledge()[a][b][c][d][e];
        return new PrisonerGenetic(knowledge);
    }
}
