package ws2022.Client.Model;

import java.util.Random;

public class Dice {
    private static String color;
    public static int numDice = 1;

    static public String rollDice() {
        Random rand = new Random();
        int number = rand.nextInt(numDice + 1);
        System.out.println(number);
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
                color = "red";
                break;
            default:
                color = "";
                break;

        }
        System.out.println(color);
        return color;
    }
}
