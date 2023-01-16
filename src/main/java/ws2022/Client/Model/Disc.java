package ws2022.Client.Model;

import java.util.ArrayList;

public class Disc {
    private String cardImage;
    private String value;
    private boolean hasCover = false;

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

    public static ArrayList<String> convertToValue(ArrayList<Disc> discArrayList) {
        ArrayList<String> result = new ArrayList<>();
        for (Disc disc : discArrayList) {
            result.add(disc.value);
        }
        return result;
    }

}
