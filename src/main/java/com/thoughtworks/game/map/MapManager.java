package com.thoughtworks.game.map;
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

import com.thoughtworks.game.component.Cell;
import com.thoughtworks.game.component.CellsCreator;
import com.thoughtworks.game.rule.RuleSet;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Sooraj.Pottekat on 10/18/2017.
 *
 * @author Sooraj Pottekat
 */
public class MapManager
{
    private CellsCreator cellsCreator;
    private RuleSet ruleSet;

    public MapManager()
    {
        cellsCreator = new CellsCreator();
        ruleSet = new RuleSet();
    }

    public Map createMap(int width, int height)
    {
        return new Map(width, height);
    }

    public void insertSeedInputToMap(Map map, List<String> seeds)
    {
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        map.populateWithCells(locations);
    }

    public Map forwardOneGenerations(Map map)
    {
        Map tempMap = createSnapShotOfMap(map.getWidth(), map.getHeight(), map.getAllLocations());
        for (int y = 0; y < map.getHeight(); y++)
        {
            for (int x = 0; x < map.getWidth(); x++)
            {
                createNextGenerationOfCell(map, tempMap, x, y);
            }
        }
        return map;
    }

    private Map createSnapShotOfMap(int width, int height, Set<Point> allLocations)
    {
        Map tempMap = createMap(width, height);
        tempMap.populateWithCells(allLocations);
        return tempMap;
    }

    private void createNextGenerationOfCell(Map map, Map tempMap, int x, int y)
    {
        Cell cell = map.getCell(x, y);
        boolean shouldLive = false;
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
            map.addCell(x, y, new Cell());
    }

    private void generateLiveCell(Map map, int x, int y, int numberOfLiveNeighbours)
    {
        boolean shouldLive;
        shouldLive = ruleSet.applyLiveCellRules(numberOfLiveNeighbours);
        if (!shouldLive)
            map.remove(x, y);
    }
}
