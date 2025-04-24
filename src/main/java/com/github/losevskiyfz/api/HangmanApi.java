package com.github.losevskiyfz.api;

import java.util.List;

public interface HangmanApi {
    void newGame();
    State getState();
    Response guessLetter(Character ch);
    List<Character> getMaskedWord();

    Integer getMistakes();
}
