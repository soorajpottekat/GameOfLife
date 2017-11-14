package com.thoughtworks.game.input;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Sooraj.Pottekat on 11/1/2017.
 *
 * @author Sooraj Pottekat
 */
public class ConsoleUserInputReader implements UserInputReader
{
    private Scanner scanner;

    public ConsoleUserInputReader(InputStream stream)
    {
        scanner = new Scanner(stream);
    }

    public String getUserInput()
    {
        return scanner.next();
    }
}
