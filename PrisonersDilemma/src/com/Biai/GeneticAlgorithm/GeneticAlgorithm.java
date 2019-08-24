package com.Biai.GeneticAlgorithm;

import com.Biai.Game.Game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm {
    int population;
    Game[] games;
    int numberOfRounds;
    int enemiesTactic;
    float mutationChance;
    int numberOfMutations;
    int numberOfGenerations;
    int selectionSize;

    public GeneticAlgorithm(int population, int numberOfRounds, int enemiesTactic, float mutationChance,
                            int numberOfMutations, int numberOfGenerations, int selectionSize) {
        this.population = population;
        this.numberOfRounds = numberOfRounds;
        this.enemiesTactic = enemiesTactic;
        this.mutationChance = mutationChance;
        this.numberOfMutations = numberOfMutations;
        if(numberOfMutations>1024)
            this.numberOfMutations=1024;
        this.numberOfGenerations = numberOfGenerations;
        this.selectionSize = selectionSize;
        createGames();
    }

    private void createGames() {
        games = new Game[population];
        for (int i = 0; i < population; i++) {
            games[i] = new Game(enemiesTactic);
        }
    }

    private void doRounds() {

        for (Game game : games) {
            for (int i = 0; i < numberOfRounds; i++) {
                game.round();
            }
            System.out.print(game.getPrisonerGenetic().getPoints()+" ");
        }
    }

    private final ArrayList<PrisonerGenetic> getTheBestPrisoners() {
        ArrayList<PrisonerGenetic> prisoners = new ArrayList<PrisonerGenetic>();
        Arrays.sort(games, Comparator.comparing(Game::GetPrisonerGeneticPoints).reversed());
        for(int i=0;i<selectionSize;i++)
            prisoners.add(games[i].getPrisonerGenetic());
        return prisoners;
    }
    //TODO population choose algorithm
    private void createNewGeneration() {
        ArrayList<PrisonerGenetic> prisoners = getTheBestPrisoners();
        Game[]games=new Game[population];
        Random r=new Random();
        for (int i = 0; i < population; i++) {
            games[i]=new Game(enemiesTactic,prisoners.get(r.nextInt(prisoners.size())).crossover(prisoners.get(r.nextInt(prisoners.size()))));
        }
        this.games=games;
    }

    private void mutateGeneration() {
        Random random = new Random();
        for (Game game : games) {
            if (random.nextInt(100) < mutationChance) {
                game.getPrisonerGenetic().mutate(numberOfMutations);
            }
        }
    }

    private void writeDataToFile(String pathFile) throws IOException//TODO
    {
        FileWriter fileWriter = new FileWriter(pathFile);

        for (int i = 0; i < population; i++) {
            fileWriter.write(i + ": memory: ");
        }
    }

    public void execute(String filePath) throws IOException {
        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println();
            doRounds();
            createNewGeneration();
            mutateGeneration();
        }
        writeDataToFile(filePath + "plik.txt");

    }
}
