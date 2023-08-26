package app.game.carmensandiego.model;

import java.util.Random;

public class RandomProvider {
    public Random getRnd() {
        return new Random(System.nanoTime());
    }
}
