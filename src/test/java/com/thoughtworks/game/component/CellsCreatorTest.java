package com.thoughtworks.game.component;

import com.thoughtworks.game.component.CellsCreator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sooraj.Pottekat on 10/20/2017.
 *
 * @author Sooraj Pottekat
 */
public class CellsCreatorTest
{

    private static CellsCreator cellsCreator;

    @BeforeClass
    public static void setUp()
    {
        cellsCreator = new CellsCreator();
    }

    @Test
    public void createLocationsWithEmptyArray() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        assertTrue(locations.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void crateLocationWithInvalidInput() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        seeds.add("2S,3");
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
    }

    @Test
    public void createLocationsWithOneEntry() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        assertEquals(1,locations.size());
    }

    @Test
    public void createLocationsWithMultipleEntry() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,1");
        seeds.add("2,2");
        seeds.add("2,5");
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        assertEquals(4,locations.size());
    }


}