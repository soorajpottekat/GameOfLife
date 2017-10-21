package com.thoughtworks.game.component;

/**
 * Created by Sooraj.Pottekat on 10/16/2017.
 *
 * @author Sooraj Pottekat
 */
public class Cell
{

    private boolean isAlive;

    public Cell()
    {
        isAlive = true;
    }
    public boolean isAlive()
    {
        return isAlive;
    }

    public void dies()
    {
        isAlive = false;
    }

    public void reproduce()
    {
        isAlive = true;
    }
}
