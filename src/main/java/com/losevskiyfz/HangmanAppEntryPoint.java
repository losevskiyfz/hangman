package com.losevskiyfz;

import com.losevskiyfz.view.GameCanvas;

public class HangmanAppEntryPoint {
    public static void main(String[] args) {
        new GameCanvas().guessLetter('z');
    }
}
