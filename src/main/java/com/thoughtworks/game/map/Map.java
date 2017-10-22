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

import java.awt.*;
import java.util.*;

/**
 * Created by Sooraj.Pottekat on 10/18/2017.
 *
 * @author Sooraj Pottekat
 */
public class Map
{
    private final int width;
    private final int height;
    private HashMap<Point, Object> cellsStore;

    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;
        cellsStore = new HashMap<Point, Object>();
    }

    public void addCell(int x, int y, Object cell)
    {
        if (isCoOrdinateInvalid(x, y))
            throw new IllegalArgumentException(
                    " Trying to insert cell in a invalid map location x = " + x + " y = " + y);
        cellsStore.put(new Point(x, y), cell);
    }

    private boolean isCoOrdinateInvalid(int x, int y)
    {
        return x >= width || x < 0 || y >= height || y < 0;
    }

    public Object getCell(int x, int y)
    {
        return cellsStore.get(new Point(x, y));
    }


    public int getNumberOfLiveNeighbours(int x, int y)
    {
        int neighbourCount = 0;
        // Checking the surrounding cells
        neighbourCount = isCellAlive(x-1,y-1)? ++neighbourCount:neighbourCount;//top left
        neighbourCount = isCellAlive(x-1,y)? ++neighbourCount:neighbourCount;//top center
        neighbourCount = isCellAlive(x-1,y+1)? ++neighbourCount:neighbourCount;//top right
        neighbourCount = isCellAlive(x,y-1)? ++neighbourCount:neighbourCount;//left
        neighbourCount = isCellAlive(x,y+1)? ++neighbourCount:neighbourCount;//right
        neighbourCount = isCellAlive(x+1,y-1)? ++neighbourCount:neighbourCount;//bottom left
        neighbourCount = isCellAlive(x+1,y)? ++neighbourCount:neighbourCount;//bottom center
        neighbourCount = isCellAlive(x+1,y+1)? ++neighbourCount:neighbourCount;//bottom right
        return neighbourCount;
    }

    private boolean isCellAlive(int neighbourX, int neighbourY)
    {
        if (!isCoOrdinateInvalid(neighbourX, neighbourY))
        {
            Object cell = getCell(neighbourX, neighbourY);
            return cell != null;
        }
        return false;
    }

    public void clear()
    {
        cellsStore.clear();
    }

    public HashMap<Point, Object> getAllCells()
    {
        return cellsStore;
    }

    public void populateWithCells(Set<Point> locations)
    {
        for (Point location : locations)
        {
            createCell(location);
        }
    }

    private void createCell(Point location)
    {
        addCell(location.x, location.y, new Object());
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Set<Point> getAllLocations()
    {
        return cellsStore.keySet();
    }

    public void remove(int x, int y)
    {
        cellsStore.remove(new Point(x, y));
    }
}
