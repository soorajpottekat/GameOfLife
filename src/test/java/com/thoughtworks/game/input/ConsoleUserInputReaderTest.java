package com.thoughtworks.game.input;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 11/1/2017.
 *
 * @author Sooraj Pottekat
 */
public class ConsoleUserInputReaderTest
{
    private static ConsoleUserInputReader consoleUserInputReader;

    @BeforeClass
    public static void setUp()
    {
        consoleUserInputReader = new ConsoleUserInputReader();
    }

    @AfterClass
    public static void cleanUp()
    {
        System.setIn(System.in);
    }

    @Test
    public void getUserInputOneLetterInput() throws Exception
    {
        ByteArrayInputStream input = new ByteArrayInputStream("d".getBytes());
        System.setIn(input);
        String userInput = new ConsoleUserInputReader().getUserInput();
        assertEquals("d",userInput);
    }

    @Test
    public void getUserInputOneStringInput() throws Exception
    {
        ByteArrayInputStream input = new ByteArrayInputStream("hello".getBytes());
        System.setIn(input);
        String userInput = new ConsoleUserInputReader().getUserInput();
        assertEquals("hello",userInput);
    }

    @Test
    public void getUserInputMinusOne() throws Exception
    {
        ByteArrayInputStream input = new ByteArrayInputStream("-1".getBytes());
        System.setIn(input);
        String userInput = new ConsoleUserInputReader().getUserInput();
        assertEquals("-1",userInput);
    }

}
