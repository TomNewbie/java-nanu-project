package ws2022.Server;

import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.GameManager;

public class Helper {
    public static void generateCoverHashMap(Coordinate[] coords) {
        for (int i = 0; i < coords.length; i++) {
            int index = Coordinate.convertToIndex(coords[i]);
            GameManager.coverHashMap.put(GameManager.colorImage[i], index);
        }
    }

    public static void updateCoverHashMap(Coordinate coord) {
        int index = Coordinate.convertToIndex(coord);
        GameManager.coverHashMap.remove(GameManager.COLOR, index);
    }
}
