package com.thoughtworks.game.display;

import com.thoughtworks.game.map.Map;
import com.thoughtworks.game.output.MessagePrinter;
import com.thoughtworks.game.output.Printer;

/**
 * Created by Sooraj.Pottekat on 10/21/2017.
 *
 * @author Sooraj Pottekat
 */
public class GameOfLifePrinter
{
    private final Printer printer;
    private final MessagePrinter messagePrinter;

    public GameOfLifePrinter(Printer printer, MessagePrinter messagePrinter)
    {
        this.printer = printer;
        this.messagePrinter = messagePrinter;
    }

    public void printResult(Map map)
    {
        for (int y = 0; y < map.getHeight(); y++)
        {
            for (int x = 0; x < map.getWidth(); x++)
                printOneCell(map.getCell(x, y));
            printer.printNewLine();
        }
    }

    private void printOneCell(Object cell)
    {
        if (cell == null)
            printer.printDeadCell();
        else
            printer.printLiveCell();
    }

    public void printUserMessage()
    {
        messagePrinter.printUserMessage();
    }
}
