package com.thoughtworks.game.main;

import com.thoughtworks.game.controller.GameController;
import com.thoughtworks.game.map.Map;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Sooraj.Pottekat on 10/23/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameEngineTest
{
    @Mock
    GameController gameController;

    @Test
    public void initialiseCodeWithApplicationArguments() throws Exception
    {
        GameEngine gameEngine = new GameEngine(null);
        String[] inputArguments = {"src/test/java/resource/testSeed", "5", "6"};
        gameEngine.initialise(inputArguments);
        verifyFilePath(gameEngine, "src/test/java/resource/testSeed");
        verifyWidth(gameEngine, 5);
        verifyHeight(gameEngine, 6);
    }
    private void verifyFilePath(GameEngine gameEngine, String expected) throws NoSuchFieldException, IllegalAccessException
    {
        Object actualPath = getFilePath(gameEngine,"filePath");
        assertEquals(expected,actualPath);
    }

    private Object getFilePath(Object object, String fieldToGet) throws NoSuchFieldException, IllegalAccessException
    {
        Field field = object.getClass().getDeclaredField(fieldToGet);
        field.setAccessible(true);
        return field.get(object);
    }

    private void verifyWidth(GameEngine gameEngine, int expected) throws NoSuchFieldException, IllegalAccessException
    {
        Object actualWidth = getFilePath(gameEngine,"mapWidth");
        assertEquals(expected,actualWidth);
    }

    private void verifyHeight(GameEngine gameEngine, int expected) throws NoSuchFieldException, IllegalAccessException
    {
        Object actualHeight = getFilePath(gameEngine,"mapHeight");
        assertEquals(expected,actualHeight);
    }

    @Test
    public void initialiseCodeWithNoApplicationParameters() throws Exception
    {
        GameEngine gameEngine = new GameEngine(null);
        String[] inputArguments = {};
        gameEngine.initialise(inputArguments);
        verifyFilePath(gameEngine, "src/main/resources/seed");
        verifyWidth(gameEngine, 20);
        verifyHeight(gameEngine, 10);
    }
    @Test
    public void initialiseCodeWithWrongApplicationParameters() throws Exception
    {
        GameEngine gameEngine = new GameEngine(null);
        String[] inputArguments = {"test","5","hello"};
        gameEngine.initialise(inputArguments);
        verifyFilePath(gameEngine, "src/main/resources/seed");
        verifyWidth(gameEngine, 20);
        verifyHeight(gameEngine, 10);
    }

    @Test
    public void startGame() throws Exception
    {
        gameController = mock(GameController.class);
        GameEngine gameEngine = new GameEngine(gameController);
        String[] inputArguments = {"src/test/java/resource/testSeed", "5", "5"};
        gameEngine.initialise(inputArguments);
        gameEngine.startGame();
        verify(gameController,times(1)).readSeedInput("src/test/java/resource/testSeed");
        List<String> seed =new ArrayList<String>();
        verify(gameController,times(1)).initialiseMapWithSeed(seed,5,5);
        verify(gameController,times(1)).printMap(null);
        verify(gameController,times(1)).forwardGenerations(null);
    }

}