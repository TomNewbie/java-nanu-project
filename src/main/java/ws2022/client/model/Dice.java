package ws2022.client.model;

import java.util.Random;

public class Dice {
    private int color;

    public Dice() {
    };

    public int getDiceValues() {
        return this.color;
    }

    public void rollDice() {
        Random rand = new Random();
        int newColor = rand.nextInt(6);
        this.color = newColor;
    }
}
