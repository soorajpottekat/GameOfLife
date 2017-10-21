package com.thoughtworks.game.main;
/*
 * Copyright (c) Multichoice Technical Operations. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Multichoice Technical Operations. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you
 * entered into with Multichoice Technical Operations.
 *
 * MULTICHOICE MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. MULTICHOICE
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT
 * OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

import com.thoughtworks.game.display.GameOfLifePrinter;
import com.thoughtworks.game.input.InputReader;
import com.thoughtworks.game.input.SeedFileReader;
import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.map.MapManager;

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
    private static final int MAP_WIDTH = 100;
    private static final int MAP_HEIGHT = 100;
    private static final int DEFAULT_NUMBER_OF_GENERATIONS = 100;

    private String filePath;
    private int mapWidth;
    private int mapHeight;
    private int numberOfGenerations;

    public static void main(String[] args)
    {
        GameEngine gameEngine = new GameEngine();
        gameEngine.initialise(args);
        gameEngine.startGame();
    }

    private void initialise(String[] applicationInput)
    {
        try
        {
            if (applicationInput.length > 4)
            {
                filePath = applicationInput[0];
                mapWidth = Integer.valueOf(applicationInput[1]);
                mapHeight = Integer.valueOf(applicationInput[2]);
                numberOfGenerations = Integer.valueOf(applicationInput[3]);
            } else
            {
                initialiseDefaultValues();
            }
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
        numberOfGenerations = DEFAULT_NUMBER_OF_GENERATIONS;
    }

    private void startGame()
    {
        MapManager mapManager = new MapManager();
        List<String> seed = readSeedInput(filePath);
        Map map = initialiseMapWithSeed(mapManager, seed);
        Map newGenMap = mapManager.forwardNGenerations(map, numberOfGenerations);
        printMap(newGenMap);
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
}
