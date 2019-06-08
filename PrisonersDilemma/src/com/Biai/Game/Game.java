package com.Biai.Game;

import com.Biai.GeneticAlgorithm.PrisonerGenetic;

public class Game {
    PrisonerGenetic prisonerGenetic =new PrisonerGenetic();
    Prisoner prisoner = new Prisoner(1);

    public void round(){
        //true - cooperate
        //false - betray
        boolean p1 = prisonerGenetic.play();
        boolean p2 = prisoner.play();
        if(p1&&p2){
            prisonerGenetic.roundEnd(0,3);
            prisoner.addPoints(3);
        }
        else if(p1&&!p2){
            prisonerGenetic.roundEnd(1,0);
            prisoner.addPoints(5);
        }
        else if (!p1&&p2){
            prisonerGenetic.roundEnd(2,5);
            prisoner.addPoints(0);
        }
        else if (!p1&&!p2){
            prisonerGenetic.roundEnd(3,1);
            prisoner.addPoints(1);
        }
    }
}