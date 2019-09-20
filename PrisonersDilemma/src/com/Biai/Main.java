package com.Biai;

import com.Biai.GeneticAlgorithm.*;

public class Main {
    public static void main(String[] args) {
        String path="C:\\";
        Integer population = 10;
        int numberOfRounds = 50;
        int enemiesTactic = 5;
        float mutationChance = 10f;
        int numberOfMutations = 20;
        int numberOfGenerations=50;
        int selectionSize=5;
        if(args.length==8)
        {
            path=args[0];
            population = Integer.valueOf(args[1]);
            numberOfRounds = Integer.valueOf(args[2]);
            enemiesTactic = Integer.valueOf(args[3]);
            mutationChance = Float.valueOf(args[4]);
            numberOfMutations = Integer.valueOf(args[5]);
            numberOfGenerations=Integer.valueOf(args[6]);
            selectionSize=Integer.valueOf(args[7]);
        }
        PrisonerGenetic[] prisoners =new PrisonerGenetic[population];
        for (int i=0;i<population;i++) {
            prisoners[i]=new PrisonerGenetic();
        }
        GeneticAlgorithm GA=new GeneticAlgorithm(population,numberOfRounds,enemiesTactic,mutationChance,numberOfMutations,numberOfGenerations,selectionSize);
        try {
            GA.execute(path);
        }catch (Exception e){}
        System.console().readLine();
    }
}
