package com.losevskiyfz.api;

import com.losevskiyfz.view.GameCanvas;

import java.util.Scanner;

public class HangmanGame {

    private final Scanner scanner = new Scanner(System.in);

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

    private void startGame() {
        GameCanvas gameCanvas = new GameCanvas();
        while (!gameCanvas.isGameOver()) {
            System.out.println();
            System.out.println("Enter a letter: ");
            gameCanvas.guessLetter(getSymbol());
        }
        if (gameCanvas.isWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
        showInterface();
    }

    private void exitGame() {
        System.exit(0);
    }
}
