package com.thoughtworks.game.output;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 11/1/2017.
 *
 * @author Sooraj Pottekat
 */
public class ConsolePrinterTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static Printer printer;

    @BeforeClass
    public static void setUp()
    {
        printer = new ConsolePrinter();
    }

    @Before
    public void setUpForTest() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpForTest() {
        System.setOut(null);
    }

    @Test
    public void printNewLine() throws Exception
    {
        printer.printNewLine();
        assertEquals("\n", outContent.toString());
    }

    @Test
    public void printLiveCell() throws Exception
    {
        printer.printLiveCell();
        assertEquals("#", outContent.toString());
    }

    @Test
    public void printDeadCell() throws Exception
    {
        printer.printDeadCell();
        assertEquals("-", outContent.toString());
    }

}