package com.Biai;

import com.Biai.GeneticAlgorithm.*;

public class Main {
    public static void main(String[] args) {
        int population = 100;
        int numberOfRounds = 10;
        int enemiesTactic = 5;
        float mutationChance = 50f;
        int numberOfMutations = 20;
        PrisonerGenetic[] prisoners =new PrisonerGenetic[population];
        for (int i=0;i<population;i++) {
            prisoners[i]=new PrisonerGenetic();
        }
        GeneticAlgorithm GA=new GeneticAlgorithm(population,numberOfRounds,enemiesTactic,mutationChance,numberOfMutations,50,5);
        try {
            GA.execute("C:\\");
        }catch (Exception e){}
    }
}
