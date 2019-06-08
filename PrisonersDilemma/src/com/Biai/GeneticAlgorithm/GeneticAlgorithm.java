package com.Biai.GeneticAlgorithm;

public class GeneticAlgorithm {
    int population;
    int numberOfRounds;
    int enemiesTactic;
    float mutationChance;
    int numberOfMutations;

    public GeneticAlgorithm(int population, int numberOfRounds, int enemiesTactic, float mutationChance, int numberOfMutations) {
        this.population = population;
        this.numberOfRounds = numberOfRounds;
        this.enemiesTactic = enemiesTactic;
        this.mutationChance = mutationChance;
        this.numberOfMutations = numberOfMutations;
    }
}
