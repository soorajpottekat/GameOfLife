package com.thoughtworks.game.component;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Sooraj.Pottekat on 10/20/2017.
 *
 * @author Sooraj Pottekat
 */
public class CellsCreator
{
    public Set<Point> createCellsLocationFromSeed(List<String> seeds)
    {
        Set<Point> mapLocations = new HashSet<Point>();
        for(String seed : seeds)
            mapLocations.add(createLocation(seed));
        return mapLocations;
    }

    private Point createLocation(String seed)
    {
        try
        {
            StringTokenizer stringTokenizer = new StringTokenizer(seed);
            int x = Integer.parseInt(stringTokenizer.nextToken(","));
            int y = Integer.parseInt(stringTokenizer.nextToken(","));
            return new Point(x, y);
        }
        catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException("The input seed data is incorrect : " + seed);
        }
    }
}
