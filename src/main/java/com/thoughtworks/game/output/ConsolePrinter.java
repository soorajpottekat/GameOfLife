package com.thoughtworks.game.output;
/**
 * Created by Sooraj.Pottekat on 11/1/2017.
 *
 * @author Sooraj Pottekat
 */
public class ConsolePrinter implements Printer
{
    public void printNewLine()
    {
        System.out.print("\n");
    }

    public void printLiveCell()
    {
        System.out.print("#");
    }

    public void printDeadCell()
    {
        System.out.print("-");
    }
}
