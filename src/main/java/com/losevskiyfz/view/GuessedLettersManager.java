package com.losevskiyfz.view;

import java.util.*;

public class GuessedLettersManager {

    private Map<Character, List<Integer>> letterIndexesMap;
    private char[] guessedLetters;

    public GuessedLettersManager(String hiddenWord) {
        initLetterIndexesMap(hiddenWord);
        initGuessedLetters(hiddenWord.length());
    }

    private void initLetterIndexesMap(String hiddenWord) {
        char[] hiddenLetters = hiddenWord.toCharArray();
        letterIndexesMap = new HashMap<>();

        for (int i = 0; i < hiddenLetters.length; i++) {
            List<Integer> listToPut = letterIndexesMap.getOrDefault(
                    hiddenLetters[i],
                    new ArrayList<>()
            );
            listToPut.add(i);
            letterIndexesMap.put(
                    hiddenLetters[i],
                    listToPut
            );
        }
    }

    private void initGuessedLetters(int length) {
        guessedLetters = new char[length];
        Arrays.fill(guessedLetters, '_');
    }

    public boolean guessLetter(char letter) {
        if (letterIndexesMap.containsKey(letter)) {
            for (Integer index : letterIndexesMap.get(letter)) {
                guessedLetters[index] = letter;
            }
            return true;
        }
        return false;
    }

    public String getGuessedLettersView() {
        return String.valueOf(guessedLetters);
    }

}
