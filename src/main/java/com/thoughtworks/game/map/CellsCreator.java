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

import com.thoughtworks.game.component.component.Cell;

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

    public Set<Point> createSeedsForMap(List<String> seeds)
    {
        Set<Point> mapLocations = new HashSet<Point>();
        for(String seed : seeds)
        {
            mapLocations.add(createLocation(seed));
        }
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
