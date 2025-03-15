package com.losevskiyfz.api;

import com.losevskiyfz.view.GameCanvas;

import java.util.Scanner;

public class HangmanGame {
    private final Scanner scanner = new Scanner(System.in);
    private GameCanvas gameCanvas;
    private static final char START_GAME = '1';
    private static final char EXIT_GAME = '2';

    public void run() {
        showIntro();
        showInterface();
        scanner.close();
    }

    private void showInterface() {
        boolean doAgain = true;
        while (doAgain) {
            showMenu();
            switch (getSymbol()) {
                case START_GAME:
                    startGame();
                    doAgain = false;
                    break;
                case EXIT_GAME:
                    doAgain = false;
                    break;
                default:
                    break;
            }
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
        System.out.print("Enter number: ");
    }

    private Character getSymbol() {
        return scanner.next().charAt(0);
    }

    private boolean isSymbolEnglishLetter(Character symbol) {
        boolean isSymbolEnglishLetter = String.valueOf(symbol).matches("[A-Za-z]");
        if (!isSymbolEnglishLetter) {
            System.out.println();
            System.out.println("Invalid symbol");
        }
        return isSymbolEnglishLetter;
    }

    private boolean isSymbolTried(Character symbol) {
        if (gameCanvas == null) {
            return false;
        }
        boolean isLetterTried = gameCanvas.isLetterTried(symbol);
        if (isLetterTried) {
            System.out.println();
            System.out.println("The letter is tried!");
        }
        return isLetterTried;
    }

    private boolean isSymbolValid(Character symbol) {
        return isSymbolEnglishLetter(symbol) && !isSymbolTried(symbol);
    }

    private void showGameResult() {
        if (gameCanvas == null) return;
        System.out.println();
        if (gameCanvas.isWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    private void acceptUserMoves() {
        while (!gameCanvas.isGameOver()) {
            System.out.println();
            System.out.print("Enter a letter: ");
            Character symbol = getSymbol();
            if (!isSymbolValid(symbol)){
                continue;
            }
            gameCanvas.guessLetter(symbol);
        }
    }

    private void startGame() {
        gameCanvas = new GameCanvas();
        acceptUserMoves();
        showGameResult();
        showInterface();
    }
}
