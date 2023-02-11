package ws2022.Client.Model;

import java.util.Random;

/**
 * Create a dice for playing game
 * 
 * The Dice class has the method rollDice() returns a random string of five
 * different color, which is a simulation of rolling a dice.
 * 
 * @return a random dice's color or joker image
 * @see color
 */

public class Dice {
    private static String color;
    public static int numDice = 2;

    /**
     * 
     * Simulates rolling a dice and returns a color based on the result.
     * 
     * @return the color obtained from rolling the dice
     */
    static public String rollDice() {
        // creates a new random number generator
        Random rand = new Random();
        // generates a random integer between 0 (inclusive) and numDice+1 (exclusive)
        int number = rand.nextInt(numDice + 1);
        // prints the random integer to the console
        System.out.println(number);
        // assigns a color based on the random integer
        switch (number) {
            case 1:
                color = "red";
                break;
            case 2:
                color = "green";
                break;
            case 3:
                color = "blue";
                break;
            case 4:
                color = "yellow";
                break;
            case 5:
                color = "orange";
                break;
            case 0:
                color = "joker";
                break;
            default:
                color = "";
                break;

        }
        // prints the color to the console
        System.out.println(color);
        // returns the color
        return color;
    }
}
