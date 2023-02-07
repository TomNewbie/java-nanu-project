package ws2022.Server;

import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.GameManager;

/**
 * The Helper class provides two static methods to generate and update the cover
 * hash map in the GameManager.gameLogic class.
 * The cover hash map stores the mapping between the dice color and its position
 * in the game board represented by an index.
 */

public class Helper {
    /**
     * Generates the cover hash map by mapping the color of each dice to its
     * corresponding position on the game board.
     *
     * @param coords an array of Coordinate objects representing the positions of
     *               each dice on the game board.
     */
    public static void generateCoverHashMap(Coordinate[] coords) {
        for (int i = 0; i < Dice.numDice; i++) {
            int index = Coordinate.convertToIndex(coords[i]);
            GameManager.gameLogic.coverHashMap.put(GameManager.gameLogic.colorImage[i], index);
        }
    }

    /**
     * Updates the cover hash map by removing the mapping between the given
     * Coordinate and the color of the dice that was previously
     * placed at that position.
     *
     * @param coord the Coordinate object representing the position of the dice
     *              whose mapping needs to be removed from the cover hash map.
     */

    public static void updateCoverHashMap(Coordinate coord) {
        int index = Coordinate.convertToIndex(coord);
        GameManager.gameLogic.coverHashMap.remove(GameManager.gameLogic.COLOR, index);
    }
}
