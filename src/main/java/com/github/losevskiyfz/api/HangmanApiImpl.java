package com.github.losevskiyfz.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class HangmanApiImpl implements HangmanApi {
    private static final Logger LOG = LogManager.getLogger(HangmanApiImpl.class);
    private final RandomWordProvider wordProvider = new RandomWordProvider();
    private final CharValidator charValidator = new CharValidator();
    private State state = State.STOPPED;
    private List<Character> hiddenWord = randomWord();
    private List<Character> maskedWord = flushedMaskedWord();
    private Set<Character> triedLetters = new HashSet<>();
    private final int MISTAKES_LIMIT = 6;
    private Integer mistakes = 0;

    @Override
    public void newGame() {
        LOG.info("Starting a new game.");
        state = State.PLAYING;
        mistakes = 0;
        maskedWord = flushedMaskedWord();
        triedLetters = flushedTriedLetters();
        hiddenWord = randomWord();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Response guessLetter(Character ch) {
        if (!state.equals(State.PLAYING)) throw new IllegalStateException();

        // validation
        LOG.info("Letter validation.");
        if (!charValidator.isValid(ch)) return Response.INVALID;
        ch = Character.toUpperCase(ch);

        // manage attempts
        if (triedLetters.contains(ch))
            return Response.TRIED;
        triedLetters.add(ch);

        // manage correctness
        LOG.info("Verifying correctness.");
        List<Integer> indexes = getCharIndexesFromWord(hiddenWord, ch);
        if (indexes.isEmpty()) {
            mistakes++;
            if (mistakes == MISTAKES_LIMIT) state = State.LOST;
            return Response.WRONG;
        } else {
            for (Integer idx : indexes) {
                maskedWord.set(idx, ch);
            }
            if (!maskedWord.contains('_')) state = State.WON;
            return Response.RIGHT;
        }
    }

    @Override
    public List<Character> getMaskedWord() {
        return maskedWord;
    }

    @Override
    public Integer getMistakes(){
        return mistakes;
    }

    private List<Character> flushedMaskedWord() {
        return new ArrayList<>(Collections.nCopies(hiddenWord.size(), '_'));
    }

    private Set<Character> flushedTriedLetters() {
        return new HashSet<>();
    }

    private List<Character> randomWord() {
        return wordProvider.get()
                .toUpperCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    private List<Integer> getCharIndexesFromWord(List<Character> word, Character ch) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < word.size(); i++) {
            if (word.get(i).equals(ch)) result.add(i);
        }
        return result;
    }
}
