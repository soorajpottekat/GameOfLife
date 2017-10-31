package com.thoughtworks.game.input;


import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


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
    public void inputInvalidFilePath()
    {
        inputReader.setFilePath("invalid");
        inputReader.getSeed();
    }

    @Test
    public void readValidFile() throws Exception
    {
        inputReader.setFilePath("src/test/java/input/DummyInput");
        List<String> registeredEntries = inputReader.getSeed();
        assertEquals(registeredEntries.size(),0);
    }

    @Test
    public void readFileWithOneEntry() throws Exception
    {
        inputReader.setFilePath("src/test/java/input/OneEntry");
        List<String> registeredEntries = inputReader.getSeed();
        assertEquals(registeredEntries.size(),1);
    }

    @Test
    public void readFileWithMultipleEntry() throws Exception
    {
        inputReader.setFilePath("src/test/java/input/MultipleEntry");
        List<String> registeredEntries = inputReader.getSeed();
        assertEquals(registeredEntries.size(),3);
    }
}