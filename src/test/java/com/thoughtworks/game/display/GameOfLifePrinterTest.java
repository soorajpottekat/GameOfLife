package com.thoughtworks.game.display;

import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.output.ConsolePrinter;
import com.thoughtworks.game.output.MessagePrinter;
import com.thoughtworks.game.output.Printer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sooraj.Pottekat on 10/21/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameOfLifePrinterTest
{
    private GameOfLifePrinter printer;
    private MockPrinter mockPrinter;
    private MockMessagePrinter mockMessagePrinter;

    @Before
    public void setUp()
    {
        mockPrinter = new MockPrinter();
        mockMessagePrinter = new MockMessagePrinter();
        printer = new GameOfLifePrinter(mockPrinter, mockMessagePrinter);
    }

    @Test
    public void mapPrinting() throws Exception
    {
        Map map = new Map(3, 2);
        map.addCell(0, 0, new Object());
        map.addCell(0, 1, new Object());
        map.addCell(1, 0, new Object());
        map.addCell(1, 1, new Object());
        printer.printResult(map);
        String expected = "##-\n" +
                "##-\n";
        assertEquals(expected, mockPrinter.toString());
    }

    @Test
    public void mapPrintingAllDeadCells() throws Exception
    {
        Map map = new Map(3, 2);
        printer.printResult(map);
        String expected = "---\n" +
                "---\n";
        assertEquals(expected, mockPrinter.toString());
    }

    @Test
    public void mapPrintingAllLiveCells() throws Exception
    {
        Map map = new Map(3, 2);
        map.addCell(0, 0, new Object());
        map.addCell(0, 1, new Object());
        map.addCell(1, 0, new Object());
        map.addCell(1, 1, new Object());
        map.addCell(2, 0, new Object());
        map.addCell(2, 1, new Object());
        printer.printResult(map);
        String expected = "###\n" +
                "###\n";
        assertEquals(expected, mockPrinter.toString());
    }

    @Test
    public void printUserMessage() throws Exception
    {
        printer.printUserMessage();
        assertEquals("Random Message", mockMessagePrinter.toString());
    }

}

class MockPrinter implements Printer
{

    private String result = "";

    public void printNewLine()
    {
        result += "\n";
    }

    public void printLiveCell()
    {
        result += "#";
    }

    public void printDeadCell()
    {
        result += "-";
    }

    @Override
    public String toString()
    {
        return result;
    }
}

class MockMessagePrinter implements MessagePrinter
{
    String message;

    public void printUserMessage()
    {
        message = "Random Message";
    }

    @Override
    public String toString()
    {
        return message;
    }
}