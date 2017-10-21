package com.thoughtworks.game.input;


import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
 * Created by Sooraj.Pottekat on 10/17/2017.
 *
 * @author Sooraj Pottekat
 */
public class SeedFileReaderTest
{

    private static SeedFileReader inputReader =null;

    @BeforeClass
    public static void setUp()
    {
        inputReader = new SeedFileReader(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInput_Invalid_FilePath()
    {
        inputReader.setFilePath("invalid");
        inputReader.getSeed();
    }

    @Test
    public void testReadValidFile() throws Exception
    {
        inputReader.setFilePath("src/test/java/input/DummyInput");
        List<String> registeredEntries = inputReader.getSeed();
        assertEquals(registeredEntries.size(),0);
    }

    @Test
    public void testReadFileWithOneEntry() throws Exception
    {
        inputReader.setFilePath("src/test/java/input/OneEntry");
        List<String> registeredEntries = inputReader.getSeed();
        assertEquals(registeredEntries.size(),1);
    }

    @Test
    public void testReadFileWithMultipleEntry() throws Exception
    {
        inputReader.setFilePath("src/test/java/input/MultipleEntry");
        List<String> registeredEntries = inputReader.getSeed();
        assertEquals(registeredEntries.size(),3);
    }
}