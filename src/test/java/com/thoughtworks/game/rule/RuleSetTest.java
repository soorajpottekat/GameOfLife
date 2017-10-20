package com.thoughtworks.game.rule;

import org.junit.BeforeClass;
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
 * Created by Sooraj.Pottekat on 10/20/2017.
 *
 * @author Sooraj Pottekat
 */
public class RuleSetTest
{

    private static RuleSet ruleSet;

    @BeforeClass
    public static void setUp()
    {
        ruleSet = new RuleSet();
    }

    @Test
    public void test_applyRulesTestRule1() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyLiveCellRules(1);
        assertFalse(shouldKeptAlive);
    }

    @Test
    public void test_applyRulesTestRule2() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyLiveCellRules(2);
        assertTrue(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyLiveCellRules(3);
        assertTrue(shouldKeptAlive);
    }
    @Test
    public void test_applyRulesTestRule3() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyLiveCellRules(4);
        assertFalse(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyLiveCellRules(7);
        assertFalse(shouldKeptAlive);
    }

    @Test
    public void test_applyRulesTestRule4() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyDeadCellRule(3);
        assertTrue(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyDeadCellRule(2);
        assertFalse(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyDeadCellRule(5);
        assertFalse(shouldKeptAlive);
    }
}