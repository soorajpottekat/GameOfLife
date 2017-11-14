package com.thoughtworks.game.component;

import com.thoughtworks.game.component.CellsCreator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
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

    private CellsCreator cellsCreator;

    @Before
    public void setUp()
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
        Set<Point> expected = new HashSet<Point>();
        expected.add(new Point(2, 3));
        assertEquals(expected, locations);
    }

    @Test
    public void createLocationsWithMultipleEntry() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,1");
        seeds.add("2,2");
        seeds.add("2,5");
        Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        HashSet<Point> expected = new HashSet<Point>();
        expected.add(new Point(2, 3));
        expected.add(new Point(2, 1));
        expected.add(new Point(2, 5));
        expected.add(new Point(2, 2));
        assertEquals(expected, locations);
    }
}