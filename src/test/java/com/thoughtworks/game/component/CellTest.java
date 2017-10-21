package com.thoughtworks.game.component;

import org.junit.Test;

import static org.junit.Assert.*;

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
 * Created by Sooraj.Pottekat on 10/16/2017.
 *
 * @author Sooraj Pottekat
 */
public class CellTest
{
    @Test
    public void createCellStateAlive()
    {
        assertTrue(new Cell().isAlive());
    }
    @Test
    public void createdCellDies()
    {
        final Cell cell = new Cell();
        cell.dies();
        assertFalse(cell.isAlive());
    }

    @Test
    public void deadCellReproduces()
    {
        final Cell cell = new Cell();
        cell.dies();
        assertFalse(cell.isAlive());
        cell.reproduce();
        assertTrue(cell.isAlive());
    }
}