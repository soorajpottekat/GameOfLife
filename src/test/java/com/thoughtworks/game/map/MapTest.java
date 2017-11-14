package com.thoughtworks.game.map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 10/18/2017.
 *
 * @author Sooraj Pottekat
 */
public class MapTest
{

    private Object cell;
    private Map map = new Map(5, 5);

    @Before
    public void setUp()
    {
        cell = new Object();
    }

    @Test
    public void createZeroWidthHeightMap() throws Exception
    {
        Map map = new Map(0, 0);
        assertNotNull(map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createZeroWidthHeightMapInsertElement() throws Exception
    {
        Map map = new Map(0, 0);
        map.addCell(0, 0, new Object());
    }

    @Test
    public void mapOneWidthAndOneHeight() throws Exception
    {
        Map map = new Map(1, 1);
        verifyPresenceOfCell(map, 0, 0);
    }

    @Test
    public void asymmetricMapInsertCorners() throws Exception
    {
        Map map = new Map(5, 3);
        verifyPresenceOfCell(map, 0, 0);
        verifyPresenceOfCell(map, 4, 2);
        verifyPresenceOfCell(map, 0, 2);
        verifyPresenceOfCell(map, 4, 0);
    }

    private void verifyPresenceOfCell(Map map, int x, int y)
    {
        map.addCell(x, y, cell);
        Object newCell = map.getCell(x, y);
        assertEquals(cell, newCell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertNegativeXCoOrdinate() throws Exception
    {
        map.addCell(-1, 0, cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertNegativeYCoOrdinate() throws Exception
    {
        map.addCell(0, -1, cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertXCoOrdinateOutOfBound() throws Exception
    {
        map.addCell(5, 0, cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertYCoOrdinateOutOfBound() throws Exception
    {
        Object cell = new Object();
        map.addCell(0, 6, cell);
    }

    @Test
    public void oneLiveNeighbour() throws Exception
    {
        map.addCell(2, 3, cell);
        Object neighbour = new Object();
        map.addCell(2, 4, neighbour);
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2, 3);
        assertEquals(1, numberLiveNeighbours);
    }

    @Test
    public void oneDeadNeighbour() throws Exception
    {
        map.addCell(2, 3, cell);
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2, 3);
        assertEquals(0, numberLiveNeighbours);
    }

    @Test
    public void allLiveNeighbours() throws Exception
    {
        map.addCell(2, 3, cell);
        map.addCell(1, 2, new Object());
        map.addCell(1, 3, new Object());
        map.addCell(1, 4, new Object());
        map.addCell(2, 2, new Object());
        map.addCell(2, 4, new Object());
        map.addCell(3, 2, new Object());
        map.addCell(3, 3, new Object());
        map.addCell(3, 4, new Object());
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2, 3);
        assertEquals(8, numberLiveNeighbours);
    }

    @Test
    public void threeLiveFiveDeadNeighbours() throws Exception
    {
        map.addCell(2, 3, cell);
        Object neighbourPos_1X3 = new Object();
        Object neighbourPos_2X2 = new Object();
        Object neighbourPos_2X4 = new Object();
        map.addCell(1, 3, neighbourPos_1X3);
        map.addCell(2, 4, neighbourPos_2X4);
        map.addCell(2, 2, neighbourPos_2X2);
        int numberLiveNeighbours = map.getNumberOfLiveNeighbours(2, 3);
        assertEquals(3, numberLiveNeighbours);
    }
    @Test
    public void mapEqualityTest() throws Exception
    {
        Map map = new Map(5, 5);
        map.addCell(1,2,new Object());
        map.addCell(1,1,new Object());
        assertTrue(map.equals(map));
        Map identical = new Map(5, 5);
        identical.addCell(1,2,new Object());
        identical.addCell(1,1,new Object());
        assertTrue(map.equals(identical));
        Map nonIdentical = new Map(5,3);
        assertFalse(map.equals(nonIdentical));
        assertFalse(map.equals(new Object()));
    }
}