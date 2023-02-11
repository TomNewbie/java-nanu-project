package ws2022.Client.Model;

import java.util.ArrayList;

/**
 * Create dics on the board game
 * 
 * The Disc class represents a game disc, with a value and a corresponding
 * image.
 * 
 * It has properties to check if the disc has a cover, is selected as a guess,
 * and to get the image and value.
 */

public class Disc {
    /** The file name of the image of the disc */
    private String cardImage;
    /** The name of the disc */
    private String value;
    /** Indicates if the disc has a cover */
    private boolean hasCover = false;
    /** Indicates if the disc is selected as a guess */
    private boolean isGuess = false;

    /**
     * Gets the name of the disc.
     *
     * @return the name of the disc
     */
    public String getValue() {
        return value;
    };

    /**
     * Checks if the disc has a cover.
     *
     * @return true if the disc has a cover, false otherwise
     */
    public boolean checkCover() {
        return hasCover;
    }

    /**
     * Gets the image of the disc.
     *
     * @return the image of the disc
     */
    public String getImage() {
        return cardImage;
    }

    /**
     * Gets the file name of the image of the disc.
     *
     * @return the file name of the image of the disc
     */
    // The file name of image
    public String getCardImage() {
        return cardImage;
    }

    /**
     * Creates a new Disc with the given image and value.
     *
     * @param cardImage the file name of the image of the disc
     * @param value     the name of the disc
     */
    public Disc(String cardImage, String value) {
        this.cardImage = cardImage;
        this.value = value;
    }

    /**
     * Sets a cover for the disc.
     */
    public void setCover() {
        hasCover = true;
    }

    /**
     * Sets the disc as a guess.
     */
    public void setGuess() {
        isGuess = true;
    }

    /**
     * Checks if the disc is a guess.
     *
     * @return true if the disc is a guess, false otherwise
     */
    public boolean checkIsGuess() {
        return isGuess;
    }

    /**
     * Converts a list of Disc objects to a list of their values.
     *
     * @param discArrayList a list of Disc objects
     * @return a list of the values of the Disc objects
     */
    public static ArrayList<String> convertToValue(ArrayList<Disc> discArrayList) {
        ArrayList<String> result = new ArrayList<>();
        for (Disc disc : discArrayList) {
            if (!disc.checkIsGuess()) {
                result.add(disc.value);
            }
        }
        return result;
    }

}
