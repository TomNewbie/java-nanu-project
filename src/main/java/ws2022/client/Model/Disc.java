package ws2022.Client.Model;

public class Disc {
    private String cardImage;
    private String value;
    private String anotherImage;
    private int coorX;
    private int coorY;

    public String getValue() {
        return value;
    };

    public String getImage() {
        if (anotherImage == null) {
            return cardImage;
        } else {
            return anotherImage;
        }
    }

    public void setCoordinate(int coorX, int coorY) {
        this.coorX = coorX;
        this.coorY = coorY;
    }

    public Disc(String cardImage, String value) {
        this.cardImage = cardImage;
        this.value = value;
    }

    public void setAnotherImage(String anotherImage) {
        this.anotherImage = anotherImage;
    }

}
