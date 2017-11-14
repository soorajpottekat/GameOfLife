package com.thoughtworks.game.main;

import com.thoughtworks.game.controller.Controller;
import org.junit.*;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 10/23/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameEngineTest
{
    private GameEngine gameEngine;
    private MockGameController gameController;

    @Before
    public void setUp()
    {
        gameController = new MockGameController();
        gameEngine = new GameEngine(gameController);
    }

    @Test
    public void initialiseCodeWithApplicationArguments() throws Exception
    {
        String[] inputArguments = {"src/test/java/resource/testSeed", "5", "6"};
        gameEngine.startGame(inputArguments);
        verifyFilePath(gameEngine, "src/test/java/resource/testSeed");
        verifyWidth(gameEngine, 5);
        verifyHeight(gameEngine, 6);
    }

    private void verifyFilePath(GameEngine gameEngine, String expected) throws NoSuchFieldException, IllegalAccessException
    {
        Object actualPath = getFilePath(gameEngine, "filePath");
        assertEquals(expected, actualPath);
    }

    private Object getFilePath(Object object, String fieldToGet) throws NoSuchFieldException, IllegalAccessException
    {
        Field field = object.getClass().getDeclaredField(fieldToGet);
        field.setAccessible(true);
        return field.get(object);
    }

    private void verifyWidth(GameEngine gameEngine, int expected) throws NoSuchFieldException, IllegalAccessException
    {
        Object actualWidth = getFilePath(gameEngine, "mapWidth");
        assertEquals(expected, actualWidth);
    }

    private void verifyHeight(GameEngine gameEngine, int expected) throws NoSuchFieldException, IllegalAccessException
    {
        Object actualHeight = getFilePath(gameEngine, "mapHeight");
        assertEquals(expected, actualHeight);
    }

    @Test
    public void initialiseCodeWithNoApplicationParameters() throws Exception
    {
        String[] inputArguments = {};
        gameEngine.startGame(inputArguments);
        verifyFilePath(gameEngine, "./src/main/resources/seed");
        verifyWidth(gameEngine, 20);
        verifyHeight(gameEngine, 10);
    }

    @Test
    public void initialiseCodeWithWrongApplicationParameters() throws Exception
    {
        String[] inputArguments = {"test", "5", "hello"};
        gameEngine.startGame(inputArguments);
        verifyFilePath(gameEngine, "./src/main/resources/seed");
        verifyWidth(gameEngine, 20);
        verifyHeight(gameEngine, 10);
    }

    @Test
    public void startGame()
    {
        String[] inputArguments = {};
        gameEngine.startGame(inputArguments);
        assertEquals(1, gameController.timesCalled());
    }
}

class MockGameController implements Controller
{
    private int timesCalled;

    public void forwardGenerations(String filePath, int mapWidth, int mapHeight)
    {
        timesCalled++;
    }

    public int timesCalled()
    {
        return timesCalled;
    }

}