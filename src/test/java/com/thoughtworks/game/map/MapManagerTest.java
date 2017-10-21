package com.thoughtworks.game.map;

import com.thoughtworks.game.component.Cell;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

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
    public void createZeroWidthHeightMap() throws Exception
    {
        Map map = mapManager.createMap(0,0);
        assertNotNull(map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createZeroWidthHeightMapInsertElement() throws Exception
    {
        Map map = mapManager.createMap(0,0);
        map.addCell(0,0,new Cell());
    }
    @Test
    public void insertOneSeedIntoMap() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        mapManager.insertSeedInputToMap(map,seeds);
        HashMap<Point, Cell> allCells = map.getAllCells();
        assertEquals(1,allCells.size());
    }
    @Test
    public void insertSeedIntoMap() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,0");
        seeds.add("2,1");
        seeds.add("2,2");
        seeds.add("2,4");
        mapManager.insertSeedInputToMap(map,seeds);
        HashMap<Point, Cell> allCells = map.getAllCells();
        assertEquals(5,allCells.size());
    }

    @Test
    public void generateOneGeneration() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = mapManager.forwardNGenerations(map, 1);
        assertEquals(0,newGenMap.getAllCells().size());
    }

    @Test
    public void generateOneGenerationStillLife() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,4");
        seeds.add("3,3");
        seeds.add("3,4");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = mapManager.forwardNGenerations(map, 1);
        assertEquals(4,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        assertNotNull(newGenMap.getCell(3,3));
        assertNotNull(newGenMap.getCell(3,4));
    }

    @Test
    public void generateFourGenerationStillLife() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,4");
        seeds.add("3,3");
        seeds.add("3,4");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = mapManager.forwardNGenerations(map, 4);
        assertEquals(4,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        assertNotNull(newGenMap.getCell(3,3));
        assertNotNull(newGenMap.getCell(3,4));
    }

    @Test
    public void generateTwoGenerationOscillatorLife() throws Exception
    {
        Map map = mapManager.createMap(5, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("1,3");
        seeds.add("2,3");
        seeds.add("3,3");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = mapManager.forwardNGenerations(map, 1);
        assertEquals(3,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,2));
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        newGenMap = mapManager.forwardNGenerations(map, 1);
        assertEquals(3,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(1,3));
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(3,3));
    }

    @Test
    public void generateOneGenerationStillLifeAsymmetric() throws Exception
    {
        Map map = mapManager.createMap(7, 5);
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        seeds.add("2,4");
        seeds.add("3,3");
        seeds.add("3,4");
        mapManager.insertSeedInputToMap(map,seeds);
        Map newGenMap = mapManager.forwardNGenerations(map, 1);
        assertEquals(4,newGenMap.getAllCells().size());
        assertNotNull(newGenMap.getCell(2,3));
        assertNotNull(newGenMap.getCell(2,4));
        assertNotNull(newGenMap.getCell(3,3));
        assertNotNull(newGenMap.getCell(3,4));
    }
}