package com.losevskiyfz.api;

import com.losevskiyfz.view.GameCanvas;

import java.util.Scanner;

public class HangmanGame {
    private final Scanner scanner = new Scanner(System.in);
    private GameCanvas gameCanvas;

    public void run() {
        showIntro();
        showInterface();
    }

    private void showInterface(){
        showMenu();
        switch (getSymbol()) {
            case '1':
                startGame();
                break;
            case '2':
                exitGame();
            default:
                showInterface();
        }
    }

    private void showIntro() {
        System.out.println("Welcome to Hangman Game!");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("What do you want?");
        System.out.println("1. Start game");
        System.out.println("2. Exit");
    }

    private Character getSymbol() {
        return scanner.next().charAt(0);
    }

    private boolean isSymbolLetter(Character symbol) {
        return Character.isLetter(symbol);
    }

    private boolean isSymbolTried(Character symbol) {
        if(gameCanvas == null) return false;
        return gameCanvas.isLetterTried(symbol);
    }

    private boolean isSymbolValid(Character symbol) {
        return isSymbolLetter(symbol) && !isSymbolTried(symbol);
    }

    private void showGameResult(){
        if(gameCanvas == null) return;
        if (gameCanvas.isWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    private void acceptUserMoves(){
        while (!gameCanvas.isGameOver()) {
            System.out.println();
            System.out.println("Enter a letter: ");
            Character symbol = getSymbol();
            if (!isSymbolValid(symbol)) continue;
            gameCanvas.guessLetter(symbol);
        }
    }

    private void startGame() {
        gameCanvas = new GameCanvas();
        acceptUserMoves();
        showGameResult();
        showInterface();
    }

    private void exitGame() {
        System.exit(0);
    }
}
