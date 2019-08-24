package com.Biai;

import com.Biai.GeneticAlgorithm.*;

public class Main {
//TODO problem when number OfRounds>10
    public static void main(String[] args) {
        int population = 100;
        int numberOfRounds = 100;
        int enemiesTactic = 3;
        float mutationChance = 10f;
        int numberOfMutations = 20;
        PrisonerGenetic[] prisoners =new PrisonerGenetic[population];
        for (int i=0;i<population;i++) {
            prisoners[i]=new PrisonerGenetic();
        }
        GeneticAlgorithm GA=new GeneticAlgorithm(population,numberOfRounds,enemiesTactic,mutationChance,numberOfMutations,100,10);
        try {
            GA.execute("C:\\");
        }catch (Exception e){}
    }
}
