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
    private Printer printer;
    private ByteArrayOutputStream outPut;

    @Before
    public void setUp()
    {
        outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));
        printer = new ConsolePrinter();
    }
    @After
    public void cleanUp()
    {
        System.setOut(System.out);
    }

    @Test
    public void printNewLine() throws Exception
    {
        printer.printNewLine();
        assertEquals("\n", outPut.toString());
    }

    @Test
    public void printLiveCell() throws Exception
    {
        printer.printLiveCell();
        assertEquals("#", outPut.toString());
    }

    @Test
    public void printDeadCell() throws Exception
    {
        printer.printDeadCell();
        assertEquals("-", outPut.toString());
    }


}