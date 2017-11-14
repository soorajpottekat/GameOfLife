package com.thoughtworks.game.controller;

import com.thoughtworks.game.display.GameOfLifePrinter;
import com.thoughtworks.game.input.FileInputReader;
import com.thoughtworks.game.input.UserInputReader;
import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.output.MessagePrinter;
import com.thoughtworks.game.output.Printer;
import org.junit.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 11/2/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameControllerTest
{
    private GameController gameController;
    private MockFileReader fileInputReader;
    private MockUserInputReader inputReader;
    private GameOfLifePrinter gameOfLifePrinter;

    @Before
    public void setUp() throws Exception
    {
        fileInputReader = new MockFileReader();
        inputReader = new MockUserInputReader();
        gameOfLifePrinter = new GameOfLifePrinter(new MockUserOutputPrinter(),new MockMessagePrinter());
        gameController = new GameController(fileInputReader, inputReader,gameOfLifePrinter);
    }

    @Test
    public void generateZeroGeneration() throws Exception
    {
        Map map = forwardNGenerations(new String[]{"-1"});
        Map expected = initialMap();
        assertEquals(expected, map);
    }

    private Map forwardNGenerations(String[] input) throws NoSuchFieldException, IllegalAccessException
    {
        ArrayList<String> entries = createEntries();
        fileInputReader.setSeed(entries);
        inputReader.setInput(input);
        gameController.forwardGenerations("inputPath", 20, 10);
        Map map = (Map) getObjectField(gameController, "map");
        return map;
    }

    private ArrayList<String> createEntries()
    {
        ArrayList<String> entries = new ArrayList<String>();
        entries.add("1,2");
        entries.add("1,1");
        entries.add("1,3");
        return entries;
    }

    private Map initialMap()
    {
        Map expected = new Map(20, 10);
        expected.addCell(1, 2, new Object());
        expected.addCell(1, 1, new Object());
        expected.addCell(1, 3, new Object());
        return expected;
    }

    private Object getObjectField(Object object, String fieldToGet) throws NoSuchFieldException, IllegalAccessException
    {
        Field field = object.getClass().getDeclaredField(fieldToGet);
        field.setAccessible(true);
        return field.get(object);
    }

    @Test
    public void generateOneGeneration() throws Exception
    {
        Map map = forwardNGenerations(new String[]{"a", "-1"});
        Map expected = generatedMap();
        assertEquals(expected, map);
    }

    private Map generatedMap()
    {
        Map expected = new Map(20, 10);
        expected.addCell(0, 2, new Object());
        expected.addCell(1, 2, new Object());
        expected.addCell(2, 2, new Object());
        return expected;
    }

    @Test
    public void generateFourGeneration() throws Exception
    {
        Map map = forwardNGenerations(new String[]{"a", "a", "a", "a", "-1"});
        Map expected = initialMap();
        assertEquals(expected, map);
    }
}

class MockFileReader implements FileInputReader
{
    private List<String> seed;

    public void setSeed(List<String> seed)
    {
        this.seed = seed;
    }

    public List<String> getSeed(String filePath) throws IllegalArgumentException
    {
        return seed;
    }
}

class MockUserInputReader implements UserInputReader
{
    private String[] value;
    private int index;

    public void setInput(String[] value)
    {
        this.value = value;
    }

    public String getUserInput()
    {
        if (value != null)
        {
            if (index == value.length)
                index = 0;
            return value[index++];
        }
        return null;
    }
}
class MockUserOutputPrinter implements Printer
{
    String output = "";
    public void printNewLine()
    {
       output += "\n";
    }

    public void printLiveCell()
    {
        output += "#";
    }

    public void printDeadCell()
    {
        output += "-";
    }
}

class MockMessagePrinter implements MessagePrinter
{
    public void printUserMessage()
    {

    }
}