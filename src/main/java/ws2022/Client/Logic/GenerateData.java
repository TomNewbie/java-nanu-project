package ws2022.Client.Logic;

import java.util.ArrayList;
import java.util.Collections;

import ws2022.Client.Model.Disc;

public class GenerateData {
    // Add value flag ngay tren array nay

    public static String[] flagValue = { "Argentina", "Australia", "Belgium", "Brazil",
            "Cameroon", "Canada", "CostaRica",
            "Croatia", "Denmark", "Ecuador", "England", "France", "Germany", "Ghana",
            "Iran", "Japan", "Korea",
            "Mexico", "Netherland", "Poland", "Portugal", "Qatar", "SaudiArabia",
            "Senegal", "Serbia", "Spain",
            "Switzerland", "Tunisia", "Uruguay", "USA", "Wales" };

    // Add name file flag tuong ung tren array nay trong ws2022/Client/assets
    public static String[] flagFileName = { "Argentina.png", "Australia.png", "Belgium.png", "Brazil.png",
            "Cameroon.png", "Canada.png", "CostaRica.png",
            "Croatia.png", "Denmark.png", "Ecuador.png", "England.png", "France.png", "Germany.png", "ghana.png",
            "Iran.png", "Japan.png", "Korea.png",
            "Mexico.png", "Netherland.png", "Poland.png", "Portugal.png", "Qatar.png", "SaudiArabia.png",
            "Senegal.png", "Serbia.png", "Spain.png",
            "Switzerland.png", "Tunisia.png", "Uruguay.png", "USA.png", "Wales.png" };

    public static void getSortValue(ArrayList<String> value) {
        for (String i : flagValue) {
            value.add(i);
        }
        Collections.sort(value);
    }

    public static void generateDisc(ArrayList<Disc> discArray) {
        for (int i = 0; i < flagValue.length; i++) {
            discArray.add(new Disc(flagFileName[i], flagValue[i]));
        }
    }

}
