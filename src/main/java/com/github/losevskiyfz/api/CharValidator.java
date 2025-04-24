package com.github.losevskiyfz.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharValidator {
    private static final Pattern PATTERN =
            Pattern.compile(PropertiesProvider.get("hangman.words.validation.pattern"));

    public boolean isValid(Character ch){
        Matcher matcher = PATTERN.matcher(ch.toString());
        return matcher.matches();
    }
}
