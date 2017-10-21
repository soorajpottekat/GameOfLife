package com.thoughtworks.game.display;

import com.thoughtworks.game.component.Cell;
import com.thoughtworks.game.map.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/*
 * Copyright (c) Multichoice Technical Operations. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Multichoice Technical Operations. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you
 * entered into with Multichoice Technical Operations.
 *
 * MULTICHOICE MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. MULTICHOICE
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT
 * OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

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
    public void test_MapPrinting() throws Exception
    {
        Map map = new Map(2, 2);
        map.addCell(0,0,new Cell());
        map.addCell(0,1,new Cell());
        map.addCell(1,0,new Cell());
        map.addCell(1,1,new Cell());
        new GameOfLifePrinter().printResult(map);
        BufferedReader actual = new BufferedReader(new FileReader("actual"));
        BufferedReader expected = new BufferedReader(new FileReader("src/test/java/resource/expected"));
        String line;
        while((line = expected.readLine()) != null)
            assertEquals(line, actual.readLine());
        assertEquals(null, actual.readLine());
    }

}