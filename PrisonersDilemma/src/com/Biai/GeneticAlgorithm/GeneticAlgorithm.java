package com.Biai.GeneticAlgorithm;

import com.Biai.Game.Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm {
    int population ;
    Game[] games = null;
    int numberOfRounds ;
    int enemiesTactic ;
    float mutationChance ;
    int numberOfMutations ;
    int numberOfGenerations ;
    int selectionSize ;


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

    private Game[] getGames()
    {
        return games;
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
    private void createNewGeneration() {
        ArrayList<PrisonerGenetic> prisoners = getTheBestPrisoners();
        Game[]games=new Game[population];
        Random r=new Random();
        int sum=0;
        for(PrisonerGenetic prisoner:prisoners)
            sum+=prisoner.getPoints();
        for (int i = 0; i < population; i++) {
            int id=r.nextInt(sum);
            int tmp=0;
            int a=-1;
            while(tmp<=id)
            {
                a++;
                tmp+=prisoners.get(a).getPoints();
            }
            id=r.nextInt(sum);
            tmp=0;
            int b=-1;
            while(tmp<=id)
            {
                b++;
                tmp+=prisoners.get(b).getPoints();
            }
            games[i]=new Game(enemiesTactic,prisoners.get(a).crossover(prisoners.get(b)));
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

    private void writeDataToFile(String pathFile, Game[] games) //TODO
    {
        FileWriter fw = null;
        try{
            fw = new FileWriter(pathFile);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (int i = 0; i < population; i++) {
                Boolean[][][][][] knowledge = games[i].getPrisonerGenetic().getKnowledge();
                bw.write(i + ": memory: " + games[i].getPrisonerGenetic().getPoints());
                bw.newLine();
                for (int a = 0; a < 4; a++)
                    for (int b = 0; b < 4; b++)
                        for (int c = 0; c < 4; c++)
                            for (int d = 0; d < 4; d++)
                                for (int e = 0; e < 4; e++)
                                {
                                    bw.write(a + " " + b + " " + c + " " + d + " " + e + " " + knowledge[a][b][c][d][e]);
                                    bw.newLine();
                                }
                bw.write("-------------------------------------------");
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            bw.close();
            fw.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void execute(String filePath) throws IOException {
        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println();
            doRounds();
            createNewGeneration();
            mutateGeneration();
        }
        writeDataToFile(filePath + "\\plik.txt", getGames());

    }
}
