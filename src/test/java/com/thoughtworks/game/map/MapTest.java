package com.thoughtworks.game.map;

import com.thoughtworks.game.component.Cell;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

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
 * Created by Sooraj.Pottekat on 10/18/2017.
 *
 * @author Sooraj Pottekat
 */
public class MapTest
{

    private static Cell cell;
    private final Map map = new Map(5, 5);

    @BeforeClass
    public static void setUp()
    {
        cell = new Cell();
    }

    @After
    public void cleanUp() throws Exception
    {
        map.clear();
    }

    @Test
    public void mapOneWidthAndOneHeight() throws Exception
    {
        Map map = new Map(1, 1);
        map.addCell(0, 0, cell);
        Cell newCell = map.getCell(0, 0);
        assertEquals(cell, newCell);
    }

    @Test
    public void mapAsymmetricInsertCorners() throws Exception
    {
        Map map = new Map(5, 3);
        map.addCell(0, 0, cell);
        Cell newCell = map.getCell(0, 0);
        assertEquals(cell, newCell);
        map.addCell(4, 2, cell);
        newCell = map.getCell(4, 2);
        assertEquals(cell, newCell);
        map.addCell(0, 2, cell);
        newCell = map.getCell(4, 2);
        assertEquals(cell, newCell);
        map.addCell(4, 0, cell);
        newCell = map.getCell(4, 2);
        assertEquals(cell, newCell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertNegativeXCoOrdinate() throws Exception
    {
        map.addCell(-1,0, cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertNegativeYCoOrdinate() throws Exception
    {
        map.addCell(0,-1, cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertXCoOrdinateOutOfBound() throws Exception
    {
        map.addCell(5,0, cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertYCoOrdinateOutOfBound() throws Exception
    {
        Cell cell = new Cell();
        map.addCell(0,6, cell);
    }

    @Test
    public void oneLiveNeighbour() throws Exception
    {
        map.addCell(2,3,cell);
        Cell neighbour = new Cell();
        map.addCell(2,4,neighbour);
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2,3);
        assertEquals(1,numberLiveNeighbours);
    }
    @Test
    public void oneDeadNeighbour() throws Exception
    {
        map.addCell(2,3, cell);
        Cell neighbour = new Cell();
        neighbour.dies();
        map.addCell(2,4,neighbour);
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2,3);
        assertEquals(0,numberLiveNeighbours);
    }
    @Test
    public void allLiveNeighbours() throws Exception
    {
        map.addCell(2,3, cell);
        map.addCell(1,2,new Cell());
        map.addCell(1,3,new Cell());
        map.addCell(1,4,new Cell());
        map.addCell(2,2,new Cell());
        map.addCell(2,4,new Cell());
        map.addCell(3,2,new Cell());
        map.addCell(3,3,new Cell());
        map.addCell(3,4,new Cell());
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2,3);
        assertEquals(8,numberLiveNeighbours);
    }

    @Test
    public void threeLiveFiveDeadNeighbours() throws Exception
    {
        map.addCell(2,3, cell);
        Cell neighbourPos_1X2 = new Cell();
        Cell neighbourPos_1X3 = new Cell();
        Cell neighbourPos_1X4 = new Cell();
        Cell neighbourPos_2X2 = new Cell();
        Cell neighbourPos_2X4 = new Cell();
        Cell neighbourPos_3X2 = new Cell();
        Cell neighbourPos_3X3 = new Cell();
        Cell neighbourPos_3X4 = new Cell();
        neighbourPos_1X2.dies();
        neighbourPos_1X4.dies();
        neighbourPos_3X2.dies();
        neighbourPos_3X3.dies();
        neighbourPos_3X4.dies();
        map.addCell(1,2,neighbourPos_1X2);
        map.addCell(1,3,neighbourPos_1X3);
        map.addCell(1,4,neighbourPos_1X4);
        map.addCell(2,2,neighbourPos_2X2);
        map.addCell(2,4,neighbourPos_2X4);
        map.addCell(3,2,neighbourPos_3X2);
        map.addCell(3,3,neighbourPos_3X3);
        map.addCell(3,4,neighbourPos_3X4);
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2,3);
        assertEquals(3,numberLiveNeighbours);
    }
}