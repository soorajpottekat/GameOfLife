package com.thoughtworks.game.controller;

import com.thoughtworks.game.input.ConsoleUserInputReader;
import com.thoughtworks.game.map.Map;
import org.junit.*;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 11/2/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameControllerTest
{
    private static GameController gameController;
    @BeforeClass
    public static void setUp() throws Exception
    {
        gameController = new GameController(new ConsoleUserInputReader());
    }

    @AfterClass
    public static void tearDown() throws Exception
    {
    }

    @Test
    public void readSeedFileInput() throws Exception
    {
        List<String> entries = gameController.readSeedInput("src/test/java/resource/testSeed");
        assertEquals(3,entries.size());
    }
    @Test
    public void initialiseMapWithSeed() throws Exception
    {
        List<String> entries = createEntries();
        Map map = gameController.initialiseMapWithSeed(entries, 10, 50);
        final Set<Point> allLocations = map.getAllLocations();
        assertEquals(3,allLocations.size());
        assertTrue(allLocations.contains(new Point(1,2)));
        assertTrue(allLocations.contains(new Point(2,1)));
        assertTrue(allLocations.contains(new Point(1,1)));
        assertFalse(allLocations.contains(new Point(2,3)));
        assertFalse(allLocations.contains(new Point(7,6)));
        assertFalse(allLocations.contains(new Point(4,9)));
    }

    private List<String> createEntries()
    {
        List<String> entries = new ArrayList<String>();
        entries.add("1,2");
        entries.add("2,1");
        entries.add("1,1");
        return entries;
    }

    @Test
    public void printMapWithThreeEntries() throws Exception
    {
        List<String> entries = createEntries();
        Map map = gameController.initialiseMapWithSeed(entries, 3, 3);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        verifyTheInputMap(map, outContent);
        System.setOut(System.out);
    }

    private void verifyTheInputMap(Map map, ByteArrayOutputStream outContent)
    {
        gameController.printMap(map);
        String expected = "---\n" +
                "-##\n" +
                "-#-\n";
        assertEquals(expected,outContent.toString());
    }
    @Test
    public void forwardOneGenerationWithBlinkerPattern() throws Exception
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ByteArrayInputStream input = new ByteArrayInputStream("a\n-1".getBytes());
        System.setIn(input);
        gameController = new GameController(new ConsoleUserInputReader());
        List<String> entries = createEntries();
        Map map = gameController.initialiseMapWithSeed(entries, 3, 3);
        verifyTheInputMap(map, outContent);
        gameController.forwardGenerations(map);
        String expected = result();
        assertEquals(expected,outContent.toString());
        System.setOut(System.out);
        System.setIn(System.in);
    }

    private String result()
    {
        String step1 = "---\n" +
                "-##\n" +
                "-#-\n";
        String userMessage = " Enter \"-1\" to exit; press any key to continue : ";
        String step2 = "---\n" +
                "-##\n" +
                "-##\n";
        return step1 + userMessage + step2 + userMessage;
    }
}