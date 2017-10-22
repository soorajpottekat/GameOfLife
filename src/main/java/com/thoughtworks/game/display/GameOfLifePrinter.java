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

import com.thoughtworks.game.component.Cell;
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
        for (int x = 0; x < map.getHeight(); x++)
        {
            for (int y = 0; y < map.getWidth(); y++)
            {
                Cell cell = map.getCell(x, y);
                printOneCell(cell);
            }
            System.out.println();
        }
    }

    private void printOneCell(Cell cell)
    {
        if(cell == null)
            System.out.print("-");
        else
            System.out.print("X");
    }
}
