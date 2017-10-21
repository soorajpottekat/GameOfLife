package com.thoughtworks.game.display;
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

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Sooraj.Pottekat on 10/21/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameOfLifePrinter
{

    public void printResult(Map map)
    {
        Set<Point> allLocations = map.getAllLocations();
        List<Point> locations = sort(allLocations);
        printResult(locations);
    }

    private List<Point> sort(Set<Point> allLocations)
    {
        List<Point> list = new ArrayList<Point>(allLocations);
        Collections.sort(list, new PointComparator());
        return list;
    }

    private void printResult(List<Point> locations)
    {
        for (Point location : locations)
        {
            System.out.println(location);
        }
    }

    private class PointComparator implements Comparator<Point>
    {
        public int compare(Point point1, Point point2)
        {
            int result = Double.compare(point1.x,point2.x);
            if(result == 0)
                result = Double.compare(point1.y, point2.y);
            return result;
        }
    }
}
