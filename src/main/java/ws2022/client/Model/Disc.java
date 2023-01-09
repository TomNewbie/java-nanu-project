package ws2022.Client.Model;

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

}
