package com.thoughtworks.game.generator;

import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.map.MapManager;
import org.junit.Before;
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
    private MapManager mapManager;
    private GameOfLifeGenerator gameOfLifeGenerator;

    @Before
    public void setUp()
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
        mapManager.insertSeedInputToMap(map, seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(0, newGenMap.getAllCells().size());
    }

    @Test
    public void generateOneGenerationStillLife() throws Exception
    {
        Map map = new Map(5, 5);
        List<String> seeds = getStillLifeInputSeed();
        mapManager.insertSeedInputToMap(map, seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(4, newGenMap.getAllCells().size());
        verifyStillLife(newGenMap);
    }

    private void verifyStillLife(Map newGenMap)
    {
        Map expected = new Map(newGenMap.getWidth(), newGenMap.getHeight());
        expected.addCell(2, 3, new Object());
        expected.addCell(2, 4, new Object());
        expected.addCell(3, 3, new Object());
        expected.addCell(3, 4, new Object());
        assertEquals(expected, newGenMap);
    }

    private List<String> getStillLifeInputSeed()
    {
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,4");
        seeds.add("3,3");
        seeds.add("3,4");
        return seeds;
    }

    @Test
    public void generateFourGenerationStillLife() throws Exception
    {
        Map map = new Map(5, 5);
        List<String> seeds = getStillLifeInputSeed();
        mapManager.insertSeedInputToMap(map, seeds);
        Map newGenMap = forwardNGenerations(map, 4);
        assertEquals(4, newGenMap.getAllCells().size());
        verifyStillLife(newGenMap);
    }

    @Test
    public void generateTwoGenerationOscillatorLife() throws Exception
    {
        Map map = new Map(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("1,3");
        seeds.add("2,3");
        seeds.add("3,3");
        mapManager.insertSeedInputToMap(map, seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(3, newGenMap.getAllCells().size());
        Map expected = new Map(5, 5);
        expected.addCell(2, 2, new Object());
        expected.addCell(2, 3, new Object());
        expected.addCell(2, 4, new Object());
        assertEquals(newGenMap, expected);
        newGenMap = forwardNGenerations(map, 1);
        expected = new Map(5, 5);
        expected.addCell(1, 3, new Object());
        expected.addCell(2, 3, new Object());
        expected.addCell(3, 3, new Object());
        assertEquals(expected, map);
    }

    @Test
    public void generateOneGenerationStillLifeAsymmetricMap() throws Exception
    {
        Map map = new Map(7, 5);
        List<String> seeds = getStillLifeInputSeed();
        mapManager.insertSeedInputToMap(map, seeds);
        Map newGenMap = forwardNGenerations(map, 1);
        assertEquals(4, newGenMap.getAllCells().size());
        verifyStillLife(newGenMap);
    }

    private Map forwardNGenerations(Map map, int numberOfGenerations)
    {
        for (int i = 0; i < numberOfGenerations; i++)
            map = gameOfLifeGenerator.forwardOneGeneration(map);
        return map;
    }

}