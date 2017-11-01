package com.thoughtworks.game.map;

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

    public MapManager()
    {
        cellsCreator = new CellsCreator();
    }

    public void insertSeedInputToMap(Map map, List<String> seeds)
    {
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        map.populateWithCells(locations);
    }
}
