package ws2022.Client.Model;

import java.util.ArrayList;

public class Disc {
    private String cardImage;
    private String value;
    private boolean hasCover = false;
    private boolean isGuess = false;

    public String getValue() {
        return value;
    };

    public boolean checkCover() {
        return hasCover;
    }

    public String getImage() {
        return cardImage;
    }

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
