package com.thoughtworks.game.main;

import com.thoughtworks.game.controller.Controller;
import com.thoughtworks.game.controller.GameController;

/**
 * Created by Sooraj.Pottekat on 10/16/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameEngine
{
    private static final String DEFAULT_SEED_FILE_PATH = "./src/main/resources/seed";
    private static final int MAP_WIDTH = 20;
    private static final int MAP_HEIGHT = 10;

    private String filePath;
    private int mapWidth;
    private int mapHeight;

    private Controller controller;

    public GameEngine(Controller gameController)
    {
        this.controller = gameController;
    }

    private void initialise(String[] applicationInput)
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
            initialiseDefaultValues();
        }
    }

    private void initialiseDefaultValues()
    {
        filePath = DEFAULT_SEED_FILE_PATH;
        mapWidth = MAP_WIDTH;
        mapHeight = MAP_HEIGHT;
    }

    public void startGame(String[] args)
    {
        initialise(args);
        controller.forwardGenerations(filePath,mapWidth,mapHeight);
    }
}
