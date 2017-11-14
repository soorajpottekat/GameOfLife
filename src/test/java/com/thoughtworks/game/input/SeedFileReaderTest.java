package com.thoughtworks.game.input;


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

    private SeedFileReaderFile inputReader;

    @Test(expected = IllegalArgumentException.class)
    public void inputInvalidFilePath()
    {
        inputReader = new SeedFileReaderFile();
        inputReader.getSeed("invalid");
    }

    @Test
    public void readValidFile() throws Exception
    {
        inputReader = new SeedFileReaderFile();
        List<String> registeredEntries = inputReader.getSeed("src/test/java/input/DummyInput");
        assertEquals(registeredEntries.size(), 0);
    }

    @Test
    public void readFileWithOneEntry() throws Exception
    {
        inputReader = new SeedFileReaderFile();
        List<String> registeredEntries = inputReader.getSeed("src/test/java/input/OneEntry");
        assertEquals(registeredEntries.size(), 1);
    }

    @Test
    public void readFileWithMultipleEntry() throws Exception
    {
        inputReader = new SeedFileReaderFile();
        List<String> registeredEntries = inputReader.getSeed("src/test/java/input/MultipleEntry");
        assertEquals(registeredEntries.size(), 3);
    }
}