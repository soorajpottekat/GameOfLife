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
 * Created by Sooraj.Pottekat on 10/20/2017.
 *
 * @author Sooraj Pottekat
 */
public class CellsCreatorTest
{

    private static CellsCreator cellsCreator = new CellsCreator();

    @BeforeClass
    public static void setUp()
    {
        cellsCreator = new CellsCreator();
    }

    @Test
    public void test_createLocationsWithEmptyArray() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        assertTrue(locations.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_crateLocationWithInvalidInput() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        seeds.add("2S,3");
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
    }

    @Test
    public void test_createLocationsWithOneEntry() throws Exception
    {
        List<String> seeds = new ArrayList<String>();
        seeds.add("2,3");
        final Set<Point> locations = cellsCreator.createCellsLocationFromSeed(seeds);
        assertEquals(1,locations.size());
    }

    @Test
    public void test_createLocationsWithMultipleEntry() throws Exception
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