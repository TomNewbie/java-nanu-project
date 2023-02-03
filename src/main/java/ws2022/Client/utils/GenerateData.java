package ws2022.Client.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;

public class GenerateData {
    public static String[] Images;
    public static String[] Values = new String[24];

    public static void generateDataForFolder() {

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File(
                "D:\\coding_Shit\\Project\\java-nanu-project\\src\\main\\resources\\ws2022\\assets\\Theme\\"
                        + GameManager.theme);

        // Populates the array with names of files and directories
        Images = f.list();
        for (int i = 0; i < Values.length; i++) {
            String temp = Images[i].substring(0, Images[i].length() - 4); // remove .jpg, .png
            String[] temp1 = temp.split("(?=\\p{Lu})"); // split when uppercase to array
            temp = Arrays.toString(temp1); // array concat to string
            Values[i] = temp.replaceAll("[^a-zA-Z0-9\\s+]", ""); // remove all special character except blank space
        }
    }

    public static void generateDisc(ArrayList<Disc> discArray) {
        generateDataForFolder();
        for (int i = 0; i < Values.length; i++) {
            discArray.add(new Disc(Images[i], Values[i]));
        }
        System.out.println(Values.length);
    }

}
