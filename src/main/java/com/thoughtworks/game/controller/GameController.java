package com.thoughtworks.game.controller;

import com.thoughtworks.game.display.GameOfLifePrinter;
import com.thoughtworks.game.generator.GameOfLifeGenerator;
import com.thoughtworks.game.input.FileInputReader;
import com.thoughtworks.game.input.UserInputReader;
import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.map.MapManager;

import java.util.List;

/**
 * Created by Sooraj.Pottekat on 11/2/2017.
 *
 * @author Sooraj Pottekat
 *
 */
public class GameController implements Controller
{
    private UserInputReader inputReader;
    private FileInputReader fileInputReader;
    private GameOfLifePrinter gameOfLifePrinter;
    private Map map;

    public GameController(FileInputReader fileInputReader, UserInputReader inputReader, GameOfLifePrinter gameOfLifePrinter)
    {
        this.inputReader = inputReader;
        this.fileInputReader = fileInputReader;
        this.gameOfLifePrinter = gameOfLifePrinter;
    }

    public void forwardGenerations(String filePath, int mapWidth, int mapHeight)
    {
        initialiseMapWithSeed(filePath, mapWidth, mapHeight);
        printMap();
        forwardGenerations();
    }

    private void initialiseMapWithSeed(String filePath, int mapWidth, int mapHeight)
    {
        List<String> seed = fileInputReader.getSeed(filePath);
        this.map = new Map(mapWidth, mapHeight);
        new MapManager().insertSeedInputToMap(map, seed);
    }

    private void printMap()
    {
        gameOfLifePrinter.printResult(map);
    }

    private void forwardGenerations()
    {
        GameOfLifeGenerator gameOfLifeGenerator = new GameOfLifeGenerator();
        String result = getUserInput();
        while (!result.equals("-1"))
        {
            map = gameOfLifeGenerator.forwardOneGeneration(map);
            printMap();
            result = getUserInput();
        }
    }

    private String getUserInput()
    {
        gameOfLifePrinter.printUserMessage();
        return inputReader.getUserInput();
    }
}
