package com.losevskiyfz.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class NounsRandomizer {
    private static final Random RANDOM = new Random();

    public static Optional<String> getRandomNoun() {
        try {
            List<String> words = Files.readAllLines(Path.of("src\\main\\resources\\english-nouns.txt"));
            if (words.isEmpty()) {
                return Optional.empty();
            }
            int randomIndex = RANDOM.nextInt(words.size());
            return Optional.of(words.get(randomIndex));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }
}
