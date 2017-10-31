package com.thoughtworks.game.display;

import com.thoughtworks.game.map.Map;
import org.junit.After;
import org.junit.Before;
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
    private PrintStream out;
    @Before
    public void setUp() throws FileNotFoundException
    {
        out = System.out;
        System.setOut(new PrintStream(new FileOutputStream("actual")));
    }

    @After
    public void tearDown() {
        System.setOut(out);
        new File("actual").delete();
    }
    @Test
    public void mapPrinting() throws Exception
    {
        Map map = new Map(3, 2);
        map.addCell(0,0,new Object());
        map.addCell(0,1,new Object());
        map.addCell(1,0,new Object());
        map.addCell(1,1,new Object());
        new GameOfLifePrinter().printResult(map);
        BufferedReader actual = new BufferedReader(new FileReader("actual"));
        BufferedReader expected = new BufferedReader(new FileReader("src/test/java/resource/expected"));
        String line;
        while((line = expected.readLine()) != null)
            assertEquals(line, actual.readLine());
        assertEquals(null, actual.readLine());
    }

}