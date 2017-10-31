package com.thoughtworks.game.input;

import java.util.List;

/**
 * Created by Sooraj.Pottekat on 10/18/2017.
 *
 * @author Sooraj Pottekat
 */
public interface InputReader {
    List<String> getSeed() throws IllegalArgumentException;
}
