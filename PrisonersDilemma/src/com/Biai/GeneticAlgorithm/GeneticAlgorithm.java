package GeneticAlgorithm;

import Game.Game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    PrisonerGenetic[] population;
    Game[] games;
    int numberOfRounds;
    int enemiesTactic;
    float mutationChance;
    int numberOfMutations;
    int numberOfGenerations;
    int limitNumberOfPoints;

    public GeneticAlgorithm(PrisonerGenetic[] population, int numberOfRounds, int enemiesTactic, float mutationChance,
                            int numberOfMutations, int numberOfGenerations, int limitNumberOfPoints) {
        this.population = population;
        this.numberOfRounds = numberOfRounds;
        this.enemiesTactic = enemiesTactic;
        this.mutationChance = mutationChance;
        this.numberOfMutations = numberOfMutations;
        this.numberOfGenerations = numberOfGenerations;
        this.limitNumberOfPoints = limitNumberOfPoints;
        createGames();
    }
    private void createGames(){
        games = new Game[population.length];
        for(int i=0; i<population.length; i++)
        {
            games[i] = new Game(enemiesTactic);
        }
    }

    private void doRounds(){
        for(int i=0;i<numberOfRounds;i++) {
            for (Game game : games
            ) {
                game.round();
            }
        }
    }
    private final ArrayList<PrisonerGenetic> getTheBestPrisoners(){
        ArrayList<PrisonerGenetic> prisoners = new ArrayList<PrisonerGenetic>();
        for (Game game: games) {
            PrisonerGenetic prisoner = (game.getPrisonerGenetic().getPoints() > limitNumberOfPoints) ? game.getPrisonerGenetic() : null;
            if(prisoner != null)
                prisoners.add(prisoner);

        }
        return prisoners;
    }
    private void createNewGeneration(){
        ArrayList<PrisonerGenetic> prisoners = getTheBestPrisoners();
        this.population = new PrisonerGenetic[prisoners.size()];

        for(int i =0; i<prisoners.size();i++)
        {
            population[i] = prisoners.get(i);
        }
    }
    private void mutateGeneration()
    {
        Random random = new Random();
        for (Game game: games) {
            if (random.nextInt(100) < mutationChance) {
                game.getPrisonerGenetic().mutate();
            }
        }
    }
    private void writeDataToFile(String pathFile) throws IOException//TODO
    {
        FileWriter fileWriter = new FileWriter(pathFile);

        for(int i=0; i<population.length;i++)
        {
            fileWriter.write(i + ": memory: ");
        }
    }
    public void execute(String filePath) throws IOException
    {
        for(int i=0; i<numberOfGenerations;i++)
        {
            doRounds();
            createNewGeneration();
            createGames();
            mutateGeneration();
        }
        writeDataToFile("plik.txt");

    }
}
