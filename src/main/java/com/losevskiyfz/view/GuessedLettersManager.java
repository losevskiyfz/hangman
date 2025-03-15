package com.losevskiyfz.view;

import java.util.*;

public class GuessedLettersManager {

    private Map<Character, List<Integer>> letterIndexesMap;
    private char[] guessedLetters;
    private final Set<Character> triedLetters;

    public GuessedLettersManager(String hiddenWord) {
        initLetterIndexesMap(hiddenWord);
        initGuessedLetters(hiddenWord.length());
        triedLetters = new HashSet<>();
    }

    private void initLetterIndexesMap(String hiddenWord) {
        char[] hiddenLetters = hiddenWord.toCharArray();
        letterIndexesMap = new HashMap<>();

        for (int i = 0; i < hiddenLetters.length; i++) {
            List<Integer> letterIndexesList = letterIndexesMap.getOrDefault(
                    hiddenLetters[i],
                    new ArrayList<>()
            );
            letterIndexesList.add(i);
            letterIndexesMap.put(
                    hiddenLetters[i],
                    letterIndexesList
            );
        }
    }

    private void initGuessedLetters(int length) {
        guessedLetters = new char[length];
        Arrays.fill(guessedLetters, '_');
    }

    public boolean guessLetter(char letter) {
        triedLetters.add(Character.toLowerCase(letter));
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

    public boolean isLetterTried(char letter) {
        return triedLetters.contains(Character.toLowerCase(letter));
    }

}
