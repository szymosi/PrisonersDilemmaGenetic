package com.Biai.Game;

import com.Biai.GeneticAlgorithm.PrisonerGenetic;

public class Game {
    PrisonerGenetic prisonerGenetic;
    Prisoner prisoner;

    public Game(int tactic) {
        this.prisonerGenetic = new PrisonerGenetic();
        this.prisoner = new Prisoner(tactic);
    }

    public Game(int tactic,PrisonerGenetic prisonerGenetic) {
        this.prisonerGenetic = prisonerGenetic;
        this.prisoner = new Prisoner(tactic);
    }

    public void round() {
        //true - cooperate
        //false - betray
        boolean p1 = prisonerGenetic.play();
        boolean p2 = prisoner.play();
        if (p1 && p2) {
            prisonerGenetic.roundEnd(0, 3);
            prisoner.addPoints(3);
        } else if (p1 && !p2) {
            prisonerGenetic.roundEnd(1, 0);
            prisoner.addPoints(5);
        } else if (!p1 && p2) {
            prisonerGenetic.roundEnd(2, 5);
            prisoner.addPoints(0);
        } else if (!p1 && !p2) {
            prisonerGenetic.roundEnd(3, 1);
            prisoner.addPoints(1);
        }
    }

    public PrisonerGenetic getPrisonerGenetic() {
        return prisonerGenetic;
    }

    public int GetPrisonerGeneticPoints(){
        return prisonerGenetic.getPoints();
    }
}
