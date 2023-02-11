package ws2022.Client.utils;

import java.util.ArrayList;

import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;

/**
 * GenerateData is a utility class that generates data for a theme in a game.
 * 
 * It has two method, one is generateDataForFolder(), which generates an array
 * of images from a given folder. The data in the array can then be used to
 * generate game disc cards.
 *
 * The other method, generateDisc(), generates a list of disc cards using the
 * image and value data generated in the generateDataForFolder() method.
 */

public class GenerateData {
    public static String[] Images;
    public static String[] Values = new String[24];
    public static String[] FootballImages = { "Argentina.png", "Australia.png", "Belgium.png", "Brazil.png",
            "Cameroon.png", "Canada.png", "CostaRica.png", "Croatia.png", "Denmark.png", "Ecuador.png", "England.png",
            "France.png", "Germany.png", "ghana.png", "Iran.png", "Japan.png", "Korea.png", "Mexico.png",
            "Netherland.png", "Poland.png", "Portugal.png", "Qatar.png", "SaudiArabia.png", "Senegal.png" };
    public static String[] FootballValues = {
            "Argentina",
            "Australia",
            "Belgium",
            "Brazil",
            "Cameroon",
            "Canada",
            "Costa Rica",
            "Croatia",
            "Denmark",
            "Ecuador",
            "England",
            "France",
            "Germany",
            "Ghana",
            "Iran",
            "Japan",
            "Korea",
            "Mexico",
            "Netherland",
            "Poland",
            "Portugal",
            "Qatar",
            "Saudi Arabia",
            "Senegal",
    };
    public static String[] ClassicValues = {
            "Buzz Lightyear",
            "Balloon",
            "Bike",
            "Cherry",
            "Crown",
            "Cup",
            "Dog",
            "Drum",
            "Earth",
            "Fish",
            "Horse",
            "Mill",
            "Minion",
            "New Car",
            "Pen",
            "Phone",
            "Plane",
            "Radio",
            "Robot",
            "Rubber Duck",
            "Snail",
            "Snake",
            "Spongebob",
            "Star",
    };
    public static String[] ClassicImages = {
            "astronaut.jpg",
            "balloon.jpg",
            "bike.png",
            "cherry.jpg",
            "crown.jpg",
            "cup.jpg",
            "dog.jpg",
            "drum.jpg",
            "earth.jpg",
            "fish.jpg",
            "hourse.jpg",
            "mill.jpg",
            "minion.jpg",
            "newcar.jpg",
            "pen.jpg",
            "phone.jpg",
            "plane.jpg",
            "radio.jpg",
            "robot.jpg",
            "rubberDuck.jpg",
            "schnecke.jpg",
            "snake.jpg",
            "spongebob.jpg",
            "star.jpg",
    };

    public static void generateDataForFolder() {
        // File directory = new File("target/classes/ws2022/assets/Theme/" +
        // GameManager.gameLogic.theme);
        // Images = directory.list();

        // for (int i = 0; i < Values.length; i++) {
        // String temp = Images[i].substring(0, Images[i].length() - 4); // remove .jpg,
        // .png
        // String[] temp1 = temp.split("(?=\\p{Lu})"); // split when uppercase to array
        // temp = Arrays.toString(temp1); // array concat to string
        // Values[i] = temp.replaceAll("[^a-zA-Z0-9\\s+]", ""); // remove all special
        // character except blank space
        // }
        if (GameManager.gameLogic.theme == "Football") {
            Images = FootballImages;
            Values = FootballValues;
        } else if (GameManager.gameLogic.theme == "Classic") {
            Images = ClassicImages;
            Values = ClassicValues;
        }

    }

    public static void generateDisc(ArrayList<Disc> discArray) {
        generateDataForFolder();
        for (int i = 0; i < Values.length; i++) {
            discArray.add(new Disc(Images[i], Values[i]));
        }
    }

}
