package com.losevskiyfz.view;

import java.util.Map;
import java.util.Optional;

class HangmanAsciiViews {

    public Optional<String> getHangmanAsciiView(Integer numberOfLives){
        String state = HANGMAN_ASCII_VIEW_FOR_NUMBER_OF_LIVES.get(numberOfLives);
        return Optional.ofNullable(state);
    }

    private static final Map<Integer, String> HANGMAN_ASCII_VIEW_FOR_NUMBER_OF_LIVES = Map.of(
            6,
            """
                     _______
                     |     |
                     |
                     |
                     |
                     |
                    _|_
                """
            ,
            5,
            """
                     _______
                     |     |
                     |     O
                     |
                     |
                     |
                    _|_
                """
            ,
            4,
            """
                     _______
                     |     |
                     |     O
                     |     |
                     |
                     |
                    _|_
                """
            ,
            3,
            """
                     _______
                     |     |
                     |     O
                     |    /|
                     |
                     |
                    _|_
                """
            ,
            2,
            """
                     _______
                     |     |
                     |     O
                     |    /|\\
                     |
                     |
                    _|_
                """
            ,
            1,
            """
                     _______
                     |     |
                     |     O
                     |    /|\\
                     |    /
                     |
                    _|_
                """
            ,
            0,
            """
                     _______
                     |     |
                     |     O
                     |    /|\\
                     |    / \\
                     |
                    _|_
                """
    );
}
