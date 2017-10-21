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
import java.util.HashMap;

/**
 * Created by Sooraj.Pottekat on 10/18/2017.
 *
 * @author Sooraj Pottekat
 */
public class Map
{


    private final int width;
    private final int height;
    private HashMap<Point, Cell> cellsStore;

    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;
        cellsStore = new HashMap<Point, Cell>();
    }

    public void addCell(int x, int y, Cell cell)
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

    public Cell getCell(int x, int y)
    {
        return cellsStore.get(new Point(x, y));
    }


    public int getNumberOfLiveNeighbours(int x, int y)
    {
        int neighbourCount = 0;
        // Checking the surrounding cells
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if (i == 0 && j == 0) // if current cell skip the operation
                {
                    continue;
                }
                else
                {
                    int neighbourX = x + i;
                    int neighbourY = y + j;
                    if (isCellAlive(neighbourX, neighbourY))
                    {
                        neighbourCount++;
                    }
                }

        return neighbourCount;
    }

    private boolean isCellAlive(int neighbourX, int neighbourY)
    {
        if (!isCoOrdinateInvalid(neighbourX, neighbourY))
        {
            Cell cell = getCell(neighbourX, neighbourY);
            return cell != null && cell.isAlive();
        }
        return false;
    }

    public void clear()
    {
        cellsStore.clear();
    }
}
