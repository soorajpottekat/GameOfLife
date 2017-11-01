package com.thoughtworks.game.generator;
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
