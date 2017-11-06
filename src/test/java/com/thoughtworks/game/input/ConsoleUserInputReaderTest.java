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
        String inputString = "d";
        verifyUserInput(inputString);
    }

    private void verifyUserInput(String inputString)
    {
        ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String userInput = new ConsoleUserInputReader().getUserInput();
        assertEquals(inputString,userInput);
    }

    @Test
    public void getUserInputOneStringInput() throws Exception
    {
        String inputString = "hello";
        verifyUserInput(inputString);
    }

    @Test
    public void getUserInputMinusOne() throws Exception
    {
        String inputString = "-1";
        verifyUserInput(inputString);
    }

}
