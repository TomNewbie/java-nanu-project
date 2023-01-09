package ws2022.Client.Model;

public class Disc {
    private String cardImage;
    private String value;
    private boolean hasCover = false;
    private boolean isGuessed = false;

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

    public boolean checkGuessed() {
        return isGuessed;
    }
}
