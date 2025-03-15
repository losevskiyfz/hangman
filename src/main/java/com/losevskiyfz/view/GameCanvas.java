package com.losevskiyfz.view;

import static com.losevskiyfz.api.NounsRandomizer.getRandomNoun;

public class GameCanvas {
    private static final Integer INITIAL_NUMBER_OF_LIVES = 6;
    private final HangmanAsciiViews hangmanAsciiViewsHolder;
    private final GuessedLettersManager guessedLettersManager;
    private Integer numberOfLives;
    private boolean gameOver = false;
    private boolean isWon = false;

    public GameCanvas() {
        numberOfLives = INITIAL_NUMBER_OF_LIVES;
        hangmanAsciiViewsHolder = new HangmanAsciiViews();
        guessedLettersManager = new GuessedLettersManager(getRandomNoun().orElseThrow());
        redrawCanvas();
    }

    public void guessLetter(Character letter) {
        if(!guessedLettersManager.guessLetter(Character.toLowerCase(letter))) {
            numberOfLives--;
        }
        checkGameState();
        redrawCanvas();
    }

    private void checkGameState(){
        verifyWin();
        verifyLose();
    }

    private void verifyLose(){
        if(numberOfLives == 0){
            gameOver = true;
        }
    }

    private void verifyWin(){
        if(!isUnsolvedLettersThere()){
            gameOver = true;
            isWon = true;
        }
    }

    private boolean isUnsolvedLettersThere(){
        return guessedLettersManager.getGuessedLettersView().contains("_");
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

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWon(){
        return isWon;
    }

    public boolean isLetterTried(Character letter){
        return guessedLettersManager.isLetterTried(letter);
    }

}
