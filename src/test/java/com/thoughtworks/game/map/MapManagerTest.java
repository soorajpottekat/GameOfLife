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
    public void insertOneSeedIntoMap() throws Exception
    {
        Map map = new Map(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        mapManager.insertSeedInputToMap(map,seeds);
        HashMap<Point, Object> allCells = map.getAllCells();
        assertEquals(1,allCells.size());
    }
    @Test
    public void insertSeedIntoMap() throws Exception
    {
        Map map = new Map(5, 5);
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
}