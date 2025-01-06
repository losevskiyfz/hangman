package com.losevskiyfz.view;

public class GameCanvas {

    private static final Integer INITIAL_NUMBER_OF_LIVES = 6;
    private final HangmanAsciiViewsHolder hangmanAsciiViewsHolder;
    private final GuessedLettersManager guessedLettersManager;
    private Integer numberOfLives = null;

    public GameCanvas() {
        numberOfLives = INITIAL_NUMBER_OF_LIVES;
        hangmanAsciiViewsHolder = new HangmanAsciiViewsHolder();
        guessedLettersManager = new GuessedLettersManager("hangman");
        redrawCanvas();
    }

    public void guessLetter(Character letter) {
        if(!guessedLettersManager.guessLetter(letter)){
            numberOfLives--;
        }
        redrawCanvas();
    }

    private void redrawCanvas() {
        clearConsole();
        drawHangman();
        drawGuessedLetters();
    }

    private void drawHangman() {
        String hangmanView = hangmanAsciiViewsHolder.getHangmanAsciiView(numberOfLives).orElseThrow();
        System.out.println(hangmanView);
    }

    private void drawGuessedLetters() {
        System.out.println(guessedLettersManager.getGuessedLettersView());
    }

    private void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
