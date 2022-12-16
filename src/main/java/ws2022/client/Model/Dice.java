package ws2022.Client.Model;

import java.util.Random;

public class Dice {
    private int color;

    public Dice() {
    };

    public String getDiceValues() {
        String color;
        switch (this.color) {
            case 0:
                color = "red";
                break;
            case 1:
                color = "green";
                break;
            case 2:
                color = "blue";
                break;
            case 3:
                color = "yellow";
                break;
            case 4:
                color = "orange";
                break;
            case 5:
                color = "joker";
                break;
            default:
                color = "";
                break;

        }
        return color;
    }

    public void rollDice() {
        Random rand = new Random();
        int newColor = rand.nextInt(6);
        this.color = newColor;
    }
}
