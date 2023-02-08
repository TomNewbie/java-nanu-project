package ws2022.Client.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class is designed to make the game run logically.
 * <p>
 * It contains methods for setting up the board game, covers and card images, 
 * as well as methods for the game processes logically.
 * 
 * @since 08/02/2023
 */

public class GameLogic {
    public ArrayList<Disc> myList = new ArrayList<>();
    public ArrayList<String> pictureName = new ArrayList<>();
    public HashMap<String, Integer> coverHashMap = new HashMap<>();
    public int totalDisc = 24;
    public String COLOR;
    public boolean isChangeDisc = false;
    public String theme = "Football";

    public Coordinate[] setUpCover() {
        Coordinate[] coordinates = new Coordinate[5];
        int count = 0;
        while (count < Dice.numDice) {
            Random random = new Random();
            int indexList = random.nextInt(totalDisc);
            if (myList.get(indexList).checkCover())
                continue;
            myList.get(indexList).setCover();
            coordinates[count] = Coordinate.convertToCoordinate(indexList);
            System.out.println("set up cover");
            System.out.println("index: " + indexList);
            System.out.println("x, y" + ": " + coordinates[count].getColumn() + ", " + coordinates[count].getRow());
            count++;
        }
        return coordinates;
    }

    public Coordinate getCurrentColorCoord() {
        return Coordinate.convertToCoordinate(coverHashMap.get(COLOR));
    }

    public String getAnswer() {
        return myList.get(coverHashMap.get(COLOR)).getValue();
    }

    public String[] colorImage = { "red", "green", "blue", "yellow", "orange" };

    public String[] imageArray() {
        String[] result = new String[Dice.numDice];
        for (int i = 0; i < Dice.numDice; i++) {
            result[i] = colorImage[i];
        }
        return result;
    }

    public String getCardImage() {
        return myList.get(coverHashMap.get(COLOR)).getCardImage();
    }
}
