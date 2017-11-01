package com.thoughtworks.game.generator;

import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.map.MapManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sooraj.Pottekat on 11/1/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameOfLifeGeneratorTest
{
    private static MapManager mapManager;
    private static GameOfLifeGenerator gameOfLifeGenerator;
    @BeforeClass
    public static void setUp()
    {
        mapManager = new MapManager();
        gameOfLifeGenerator = new GameOfLifeGenerator();
    }
    @Test
    public void generateOneGeneration() throws Exception
    {
        Map map = new Map(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(0,newGenMap.getAllCells().size());
    }

    @Test
    public void generateOneGenerationStillLife() throws Exception
    {
        Map map = new Map(5, 5);
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
        Map map = new Map(5, 5);
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
        Map map = new Map(5, 5);
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
        Map map = new Map(7, 5);
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
            map = gameOfLifeGenerator.forwardOneGeneration(map);
        }
        return map;
    }

}