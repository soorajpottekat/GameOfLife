package com.thoughtworks.game.main;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 10/23/2017.
 *
 * @author Sooraj Pottekat
 */
@Ignore
public class GameEngineTest
{

    @Ignore
    @Test
    public void initialiseCodeWithApplicationArguments() throws Exception
    {
        GameEngine gameEngine = new GameEngine();
        String[] inputArguments = {"src/test/java/resource/testSeed", "5", "5"};
        gameEngine.initialise(inputArguments);
    }
    @Ignore
    @Test
    public void startGame() throws Exception
    {
        GameEngine gameEngine = new GameEngine();
        String[] inputArguments = {"src/test/java/resource/testSeed", "5", "5"};
        gameEngine.initialise(inputArguments);
        gameEngine.startGame();
    }

}