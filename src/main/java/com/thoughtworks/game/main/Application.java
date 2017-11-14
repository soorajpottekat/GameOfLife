package com.thoughtworks.game.main;

import com.thoughtworks.game.controller.GameController;
import com.thoughtworks.game.display.GameOfLifePrinter;
import com.thoughtworks.game.input.ConsoleUserInputReader;
import com.thoughtworks.game.input.FileInputReader;
import com.thoughtworks.game.input.SeedFileReaderFile;
import com.thoughtworks.game.input.UserInputReader;
import com.thoughtworks.game.output.ConsolePrinter;
import com.thoughtworks.game.output.UserMessagePrinter;

/**
 * Created by Sooraj.Pottekat on 11/8/2017.
 *
 * @author Sooraj Pottekat
 */
public class Application
{
    public static void main(String[] args)
    {
        GameOfLifePrinter gameOfLifePrinter = new GameOfLifePrinter(new ConsolePrinter(),new UserMessagePrinter());
        FileInputReader fileInputReader = new SeedFileReaderFile();
        UserInputReader inputReader = new ConsoleUserInputReader(System.in);
        GameController gameController = new GameController(fileInputReader, inputReader, gameOfLifePrinter);
        GameEngine gameEngine = new GameEngine(gameController);
        gameEngine.startGame(args);
    }
}
