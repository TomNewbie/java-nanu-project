package ws2022.Client.Model;

import java.util.ArrayList;

/**
 * Create dics on the board game
 * <p>
 * The Disc class represents a game disc, with a value and a corresponding image.
 * <p>
 * It has properties to check if the disc has a cover, is selected as a guess, and to get the image and value.
 * 
 * @since 08/02/2023
 */

public class Disc {
    private String cardImage;
    private String value;
    private boolean hasCover = false;
    private boolean isGuess = false;
    
    // The name of the disc
    public String getValue() {
        return value;
    };

    // Check if the disc has cover or not, to determine to 
    // put cover or move on to another picture in function setUpCover
    public boolean checkCover() {
        return hasCover;
    }

    public String getImage() {
        return cardImage;
    }
    
    // The file name of image
    public String getCardImage() {
        return cardImage;
    }

    public Disc(String cardImage, String value) {
        this.cardImage = cardImage;
        this.value = value;
    }

    public void setCover() {
        hasCover = true;
    }

    public void setGuess() {
        isGuess = true;
    }

    // Used to remove the value in Guess Picture choice box
    public boolean checkIsGuess() {
        return isGuess;
    }

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
