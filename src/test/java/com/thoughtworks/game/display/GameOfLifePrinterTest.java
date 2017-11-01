package com.thoughtworks.game.display;

import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.output.ConsolePrinter;
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
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static GameOfLifePrinter printer;

    @BeforeClass
    public static void setUp()
    {
        printer = new GameOfLifePrinter(new ConsolePrinter());
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
    public void mapPrinting() throws Exception
    {
        Map map = new Map(3, 2);
        map.addCell(0,0,new Object());
        map.addCell(0,1,new Object());
        map.addCell(1,0,new Object());
        map.addCell(1,1,new Object());
        printer.printResult(map);
        String expected = "##-\n" +
                          "##-\n";
        assertEquals(expected, outContent.toString());
    }
    @Test
    public void mapPrintingAllDeadCells() throws Exception
    {
        Map map = new Map(3, 2);
        printer.printResult(map);
        String expected = "---\n" +
                "---\n";
        assertEquals(expected, outContent.toString());
    }
    @Test
    public void mapPrintingAllLiveCells() throws Exception
    {
        Map map = new Map(3, 2);
        map.addCell(0,0,new Object());
        map.addCell(0,1,new Object());
        map.addCell(1,0,new Object());
        map.addCell(1,1,new Object());
        map.addCell(2,0,new Object());
        map.addCell(2,1,new Object());
        printer.printResult(map);
        String expected = "###\n" +
                "###\n";
        assertEquals(expected, outContent.toString());
    }

}