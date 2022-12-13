package ws2022.Client.Model;

public class Disc {
    private String cardImage;
    private String value;

    public String getValue(Disc disc) {
        return disc.value;
    };

    public Disc(String cardImage, String value) {
        this.cardImage = cardImage;
        this.value = value;
    }

}
