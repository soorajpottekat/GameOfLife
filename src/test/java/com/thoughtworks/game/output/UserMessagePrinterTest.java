package com.thoughtworks.game.output;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 11/14/2017.
 *
 * @author Sooraj Pottekat
 */
public class UserMessagePrinterTest
{
    private MessagePrinter messagePrinter;
    private ByteArrayOutputStream outPut;

    @Before
    public void setUp()
    {
        outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));
        messagePrinter = new UserMessagePrinter();
    }
    @After
    public void cleanUp()
    {
        System.setOut(System.out);
    }
    @Test
    public void printUserMessage() throws Exception
    {
        messagePrinter.printUserMessage();
        assertEquals("Enter \"-1\" to exit; press any key to continue : ",outPut.toString());
    }

}