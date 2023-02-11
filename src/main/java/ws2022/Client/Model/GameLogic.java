package ws2022.Client.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class is designed to make the game run logically.
 * 
 * It contains methods for setting up the board game, covers and card images,
 * as well as methods for the game processes logically.
 */

public class GameLogic {
    /**
     * The list of discs in the game.
     */
    public ArrayList<Disc> myList = new ArrayList<>();

    /**
     * The list of picture names in the game.
     */
    public ArrayList<String> pictureName = new ArrayList<>();

    /**
     * The map of covers in the game.
     */
    public HashMap<String, Integer> coverHashMap = new HashMap<>();

    /**
     * The total number of discs in the game.
     */
    public int totalDisc = 24;

    /**
     * The color of the current disc.
     */
    public String COLOR;

    /**
     * Indicates whether the disc has been changed.
     */
    public boolean isChangeDisc = false;

    /**
     * The theme of the game.
     */
    public String theme = "Football";

    /**
     * Sets up the covers for the game.
     * 
     * @return an array of coordinates representing the locations of the covers
     */
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

    /**
     * Returns the current color coordinate.
     * 
     * @return the coordinate of the current color
     */
    public Coordinate getCurrentColorCoord() {
        return Coordinate.convertToCoordinate(coverHashMap.get(COLOR));
    }

    /**
     * Returns the value of the current disc.
     * 
     * @return the value of the current disc
     */
    public String getAnswer() {
        return myList.get(coverHashMap.get(COLOR)).getValue();
    }

    public String[] colorImage = { "red", "green", "blue", "yellow", "orange" };

    /**
     * Returns an array of image names for the game.
     * 
     * @return an array of image names for the game
     */
    public String[] imageArray() {
        String[] result = new String[Dice.numDice];
        for (int i = 0; i < Dice.numDice; i++) {
            result[i] = colorImage[i];
        }
        return result;
    }

    /**
     * Returns the image for the current disc.
     * 
     * @return the image for the current disc
     */
    public String getCardImage() {
        return myList.get(coverHashMap.get(COLOR)).getCardImage();
    }
}
