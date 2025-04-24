package com.github.losevskiyfz.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomWordProvider {
    private static final Logger LOG = LogManager.getLogger(RandomWordProvider.class);
    private static final String FILE_PATH_PROPERTY = "hangman.words.file.path";
    private static final List<String> WORDS = loadWords();
    private static final Random RANDOM = new Random();

    public String get() {
        return WORDS.get(RANDOM.nextInt(WORDS.size()));
    }

    private static List<String> loadWords(){
        LOG.info("Loading words to the project.");
        try (InputStream in = RandomWordProvider.class.getClassLoader().getResourceAsStream(
                PropertiesProvider.get(RandomWordProvider.FILE_PATH_PROPERTY)
        );
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }
}
