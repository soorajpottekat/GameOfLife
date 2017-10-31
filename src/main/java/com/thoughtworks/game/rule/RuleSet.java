package com.thoughtworks.game.rule;

/**
 * Created by Sooraj.Pottekat on 10/20/2017.
 *
 * RULES
 * Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
 * Any live cell with two or three live neighbours lives on to the next generation.
 * Any live cell with more than three live neighbours dies, as if by overpopulation.
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 * @author Sooraj Pottekat
 */
public class RuleSet
{

    public boolean applyLiveCellRules(int numberOfLiveNeighbours)
    {
        return numberOfLiveNeighbours == 2 || numberOfLiveNeighbours == 3;
    }

    public boolean applyDeadCellRule(int numberOfLiveNeighbours)
    {
        return numberOfLiveNeighbours == 3;
    }
}
