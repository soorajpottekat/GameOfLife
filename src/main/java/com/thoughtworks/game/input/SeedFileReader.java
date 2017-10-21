package com.thoughtworks.game.input;
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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Sooraj.Pottekat on 10/17/2017.
 *
 * @author Sooraj Pottekat
 */
public class SeedFileReader implements InputReader {
    private static final Logger LOGGER = Logger.getLogger("SeedFileReader");
    private String filePath;

    public SeedFileReader(String filePath)
    {
        this.filePath = filePath;
    }
    public List<String> getSeed() throws IllegalArgumentException {
        List<String> seed;
        BufferedReader reader = null;
        try
        {
            reader = openFile(filePath);
            seed = readFileEntries(reader);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            throw new IllegalArgumentException("seed file path : " + filePath);
        }
        catch (IOException ioe)
        {
            throw new IllegalArgumentException(" Please check the file, an error occurred while reading the file");
        }
        finally {
            close(reader);
        }
        return seed;
    }
    private BufferedReader openFile(String filePath) throws FileNotFoundException
    {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader;
    }

    private List<String> readFileEntries(BufferedReader reader) throws IOException
    {
        List<String> registeredEntries = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null)
        {
            registeredEntries.add(line);
        }
        return registeredEntries;
    }

    private void close(BufferedReader reader)
    {
        if(reader != null) {
            try {
                reader.close();
            } catch (IOException ioe) {
                LOGGER.warning(" Failed to close the seed file reader");
            }
        }
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
}
