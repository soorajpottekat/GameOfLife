package com.thoughtworks.game.rule;
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
