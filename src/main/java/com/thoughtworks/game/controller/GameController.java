package com.thoughtworks.game.controller;

import com.thoughtworks.game.display.GameOfLifePrinter;
import com.thoughtworks.game.generator.GameOfLifeGenerator;
import com.thoughtworks.game.input.InputReader;
import com.thoughtworks.game.input.SeedFileReader;
import com.thoughtworks.game.input.UserInputReader;
import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.map.MapManager;
import com.thoughtworks.game.output.ConsolePrinter;

import java.util.List;

/**
 * Created by Sooraj.Pottekat on 11/2/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameController
{
    private UserInputReader inputReader;

    public GameController(UserInputReader inputReader)
    {
        this.inputReader = inputReader;
    }

    public List<String> readSeedInput(String filePath)
    {
        // read the input seed
        InputReader fileReader = new SeedFileReader(filePath);
        return fileReader.getSeed();
    }

    public Map initialiseMapWithSeed(List<String> seed, int mapWidth, int mapHeight)
    {
        // Create Map using Map Manager
        Map map = new Map(mapWidth, mapHeight);
        new MapManager().insertSeedInputToMap(map, seed);
        return map;
    }

    public void printMap(Map newGenMap)
    {
        // Display Current generation
        GameOfLifePrinter gameOfLifePrinter = new GameOfLifePrinter(new ConsolePrinter());
        gameOfLifePrinter.printResult(newGenMap);
    }

    public void forwardGenerations(Map map)
    {
        String result = "";
        GameOfLifeGenerator gameOfLifeGenerator = new GameOfLifeGenerator();
        result = getUserInput();
        while (!result.equals("-1"))
        {
            Map newGenMap = gameOfLifeGenerator.forwardOneGeneration(map);
            printMap(newGenMap);
            result = getUserInput();
        }
    }

    private String getUserInput()
    {
        System.out.print(" Enter \"-1\" to exit; press any key to continue : ");
        return inputReader.getUserInput();
    }
}
