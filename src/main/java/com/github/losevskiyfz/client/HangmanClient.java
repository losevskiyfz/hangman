package com.github.losevskiyfz.client;

import com.github.losevskiyfz.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class HangmanClient {
    private final HangmanApi api = new HangmanApiImpl();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = LogManager.getLogger(HangmanClient.class);

    public void start() {
        printWelcomeMessage();
        while (true) {
            System.out.print("Start a new game? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                playGame();
            } else if (input.equals("n")) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    private void playGame() {
        api.newGame();
        while (api.getState() == State.PLAYING) {
            printGameState();
            System.out.print("Enter a letter: ");
            String input = scanner.nextLine().trim();
            if (input.length() != 1) {
                System.out.println("Please enter only one letter.");
                continue;
            }

            char guess = input.charAt(0);
            Response response = api.guessLetter(guess);

            switch (response) {
                case RIGHT -> System.out.println("✅ Correct!");
                case WRONG -> System.out.println("❌ Wrong!");
                case TRIED -> System.out.println("⚠️ Already tried!");
                case INVALID -> System.out.println("🚫 Invalid input.");
            }
        }

        printGameState();
        if (api.getState() == State.WON) {
            System.out.println("🎉 You won!");
        } else {
            System.out.println("💀 You lost!");
        }
    }

    private void printGameState() {
        System.out.println("\nWord: " + maskToString(api.getMaskedWord()));
        System.out.println("Mistakes: " + getMistakesVisual());
    }

    private String maskToString(List<Character> maskedWord) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : maskedWord) {
            sb.append(ch).append(' ');
        }
        return sb.toString().trim();
    }

    private String getMistakesVisual() {
        int mistakes = api.getMistakes();
        return "*".repeat(mistakes) + " (" + mistakes + "/6)";
    }

    private void printWelcomeMessage() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("banner.txt")) {
            if (inputStream == null) {
                System.out.println("Welcome to Hangman!");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Welcome to Hangman! (banner failed to load)");
            LOG.error(e);
        }
    }
}
