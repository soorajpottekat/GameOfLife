package com.thoughtworks.game.display;

import com.thoughtworks.game.map.Map;

/**
 * Created by Sooraj.Pottekat on 10/21/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameOfLifePrinter
{
    public void printResult(Map map)
    {
        for (int y = 0; y < map.getHeight(); y++)
        {
            for (int x = 0; x < map.getWidth(); x++)
            {
                Object cell = map.getCell(x, y);
                printOneCell(cell);
            }
            System.out.println();
        }
    }

    private void printOneCell(Object cell)
    {
        if (cell == null)
            System.out.print("-");
        else
            System.out.print("#");
    }
}
