package com.thoughtworks.game.generator;

import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.rule.RuleSet;

import java.awt.*;
import java.util.Set;

/**
 * Created by Sooraj.Pottekat on 11/1/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameOfLifeGenerator
{
    private RuleSet ruleSet;
    public GameOfLifeGenerator()
    {
        ruleSet = new RuleSet();
    }
    public Map forwardOneGeneration(Map map)
    {
        Map tempMap = createSnapShotOfMap(map.getWidth(), map.getHeight(), map.getAllLocations());
        for (int y = 0; y < map.getHeight(); y++)
        {
            for (int x = 0; x < map.getWidth(); x++)
            {
                generateNextGenerationOfACell(map, tempMap, x, y);
            }
        }
        return map;
    }

    private void generateNextGenerationOfACell(Map map, Map tempMap, int x, int y)
    {
        Object cell = map.getCell(x, y);
        final int numberOfLiveNeighbours = tempMap.getNumberOfLiveNeighbours(x, y);
        if (cell == null)
            generateDeadCell(map, x, y, numberOfLiveNeighbours);
        else
            generateLiveCell(map, x, y, numberOfLiveNeighbours);
    }

    private void generateDeadCell(Map map, int x, int y, int numberOfLiveNeighbours)
    {
        boolean shouldLive;
        shouldLive = ruleSet.applyDeadCellRule(numberOfLiveNeighbours);
        if (shouldLive)
            map.addCell(x, y, new Object());
    }

    private void generateLiveCell(Map map, int x, int y, int numberOfLiveNeighbours)
    {
        boolean shouldLive;
        shouldLive = ruleSet.applyLiveCellRules(numberOfLiveNeighbours);
        if (!shouldLive)
            map.remove(x, y);
    }

    public Map createSnapShotOfMap(int width, int height, Set<Point> allLocations)
    {
        Map tempMap = new Map(width, height);
        tempMap.populateWithCells(allLocations);
        return tempMap;
    }
}
