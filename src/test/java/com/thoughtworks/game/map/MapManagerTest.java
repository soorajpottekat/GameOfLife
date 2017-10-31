package com.thoughtworks.game.map;

import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 10/18/2017.
 *
 * @author Sooraj Pottekat
 */
public class MapManagerTest
{
    public static MapManager mapManager = null;
    @BeforeClass
    public static void setUp()
    {
        mapManager = new MapManager();
    }
    @Test
    public void createZeroWidthHeightMap() throws Exception
    {
        Map map = mapManager.createMap(0,0);
        assertNotNull(map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createZeroWidthHeightMapInsertElement() throws Exception
    {
        Map map = mapManager.createMap(0,0);
        map.addCell(0,0,new Object());
    }
    @Test
    public void insertOneSeedIntoMap() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        mapManager.insertSeedInputToMap(map,seeds);
        HashMap<Point, Object> allCells = map.getAllCells();
        assertEquals(1,allCells.size());
    }
    @Test
    public void insertSeedIntoMap() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,0");
        seeds.add("2,1");
        seeds.add("2,2");
        seeds.add("2,4");
        mapManager.insertSeedInputToMap(map,seeds);
        HashMap<Point, Object> allCells = map.getAllCells();
        assertEquals(5,allCells.size());
    }

    @Test
    public void generateOneGeneration() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(0,newGenMap.getAllCells().size());
    }

    @Test
    public void generateOneGenerationStillLife() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,4");
        seeds.add("3,3");
        seeds.add("3,4");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(4,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        assertNotNull(newGenMap.getCell(3,3));
        assertNotNull(newGenMap.getCell(3,4));
    }

    @Test
    public void generateFourGenerationStillLife() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,4");
        seeds.add("3,3");
        seeds.add("3,4");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = forwardNGenerations(map, 4);
        assertEquals(4,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        assertNotNull(newGenMap.getCell(3,3));
        assertNotNull(newGenMap.getCell(3,4));
    }

    @Test
    public void generateTwoGenerationOscillatorLife() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("1,3");
        seeds.add("2,3");
        seeds.add("3,3");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(3,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,2));
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        newGenMap = forwardNGenerations(map, 1);
        assertEquals(3,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(1,3));
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(3,3));
    }

    @Test
    public void generateOneGenerationStillLifeAsymmetricMap() throws Exception
    {
        Map map = mapManager.createMap(7, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,4");
        seeds.add("3,3");
        seeds.add("3,4");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(4,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        assertNotNull(newGenMap.getCell(3,3));
        assertNotNull(newGenMap.getCell(3,4));
    }
    private Map forwardNGenerations(Map map, int numberOfGenerations)
    {
        for (int i = 0; i < numberOfGenerations; i++)
        {
            map = mapManager.forwardOneGenerations(map);
        }
        return map;
    }
}