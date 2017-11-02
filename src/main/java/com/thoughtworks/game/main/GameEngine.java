package com.thoughtworks.game.main;

import com.thoughtworks.game.controller.GameController;
import com.thoughtworks.game.display.GameOfLifePrinter;
import com.thoughtworks.game.generator.GameOfLifeGenerator;
import com.thoughtworks.game.input.ConsoleUserInputReader;
import com.thoughtworks.game.input.InputReader;
import com.thoughtworks.game.input.SeedFileReader;
import com.thoughtworks.game.input.UserInputReader;
import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.map.MapManager;
import com.thoughtworks.game.output.ConsolePrinter;

import java.util.List;
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
        GameController gameController = new GameController(new ConsoleUserInputReader());
        List<String> seed = gameController.readSeedInput(filePath);
        Map map = gameController.initialiseMapWithSeed(seed,mapWidth,mapHeight);
        System.out.println("Initial Map");
        gameController.printMap(map);
        gameController.forwardGenerations(map);
    }
}
