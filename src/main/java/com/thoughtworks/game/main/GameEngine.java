package com.thoughtworks.game.main;

import com.thoughtworks.game.display.GameOfLifePrinter;
import com.thoughtworks.game.input.InputReader;
import com.thoughtworks.game.input.SeedFileReader;
import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.map.MapManager;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Sooraj.Pottekat on 10/16/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameEngine
{
    private static final Logger LOGGER = Logger.getLogger("GameEngine");
    private static final String DEFAULT_SEED_FILE_PATH = "src/main/resources/seed";
    private static final int MAP_WIDTH = 20;
    private static final int MAP_HEIGHT = 10;

    private String filePath;
    private int mapWidth;
    private int mapHeight;

    public static void main(String[] args)
    {
        GameEngine gameEngine = new GameEngine();
        gameEngine.initialise(args);
        gameEngine.startGame();
    }

    public void initialise(String[] applicationInput)
    {
        if (applicationInput.length == 3)
            readAndSetInput(applicationInput);
        else
            initialiseDefaultValues();
    }

    private void readAndSetInput(String[] applicationInput)
    {
        try
        {
            filePath = applicationInput[0];
            mapWidth = Integer.valueOf(applicationInput[1]);
            mapHeight = Integer.valueOf(applicationInput[2]);
        } catch (NumberFormatException numberFormatException)
        {
            LOGGER.severe("Application's input arguments are in correct");
            initialiseDefaultValues();
        }
    }

    private void initialiseDefaultValues()
    {
        filePath = DEFAULT_SEED_FILE_PATH;
        mapWidth = MAP_WIDTH;
        mapHeight = MAP_HEIGHT;
    }

    public void startGame()
    {
        MapManager mapManager = new MapManager();
        List<String> seed = readSeedInput(filePath);
        Map map = initialiseMapWithSeed(mapManager, seed);
        System.out.println("Initial Map");
        printMap(map);
        forwardGenerations(mapManager, map);
    }

    private List<String> readSeedInput(String filePath)
    {
        // read the input seed
        InputReader fileReader = new SeedFileReader(filePath);
        return fileReader.getSeed();
    }

    private Map initialiseMapWithSeed(MapManager mapManager, List<String> seed)
    {
        // Create Map using Map Manager
        Map map = mapManager.createMap(mapWidth, mapHeight);
        mapManager.insertSeedInputToMap(map, seed);
        return map;
    }

    private void printMap(Map newGenMap)
    {
        // Display Current generation
        GameOfLifePrinter gameOfLifePrinter = new GameOfLifePrinter();
        gameOfLifePrinter.printResult(newGenMap);
    }

    private void forwardGenerations(MapManager mapManager, Map map)
    {
        String result = "";
        Scanner sc = new Scanner(System.in);
        getUserInput(sc);
        while (!result.equals("-1"))
        {
            Map newGenMap = mapManager.forwardOneGenerations(map);
            printMap(newGenMap);
            result = getUserInput(sc);
        }
    }

    private String getUserInput(Scanner sc)
    {
        String result;
        System.out.print(" Enter \"-1\" to exit press any key to continue : ");
        result = sc.next();
        return result;
    }
}
