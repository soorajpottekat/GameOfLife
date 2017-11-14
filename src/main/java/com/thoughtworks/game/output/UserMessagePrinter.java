package com.thoughtworks.game.output;
/**
 * Created by Sooraj.Pottekat on 11/14/2017.
 *
 * @author Sooraj Pottekat
 */
public class UserMessagePrinter implements MessagePrinter
{
    public void printUserMessage()
    {
        System.out.print("Enter \"-1\" to exit; press any key to continue : ");
    }
}
