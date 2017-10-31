package com.thoughtworks.game.rule;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void applyRulesTestRule1() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyLiveCellRules(1);
        assertFalse(shouldKeptAlive);
    }

    @Test
    public void applyRulesTestRule2() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyLiveCellRules(2);
        assertTrue(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyLiveCellRules(3);
        assertTrue(shouldKeptAlive);
    }
    @Test
    public void applyRulesTestRule3() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyLiveCellRules(4);
        assertFalse(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyLiveCellRules(7);
        assertFalse(shouldKeptAlive);
    }

    @Test
    public void applyRulesTestRule4() throws Exception
    {
        boolean shouldKeptAlive = ruleSet.applyDeadCellRule(3);
        assertTrue(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyDeadCellRule(2);
        assertFalse(shouldKeptAlive);
        shouldKeptAlive = ruleSet.applyDeadCellRule(5);
        assertFalse(shouldKeptAlive);
    }
}