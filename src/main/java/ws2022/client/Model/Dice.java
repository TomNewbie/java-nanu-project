package ws2022.Client.Model;

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
