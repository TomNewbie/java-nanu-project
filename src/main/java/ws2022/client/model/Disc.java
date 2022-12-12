package ws2022.client.model;

public class Disc {
    private String cardImage;
    private String value;

    public String getValue(Disc disc) {
        return disc.cardImage;
    };

    public Disc(String cardImage, String value) {
        this.cardImage = cardImage;
        this.value = value;
    }

}
