package ws2022.Client.Model;

/**
 * Coordinate class represents a coordinate in a 2D grid.
 * 
 * The grid consists of 7 rows and 7 columns with indices ranging from 0 to 6.
 */

public class Coordinate {
    /** The column of the coordinate in the grid */
    private int col;
    /** The row of the coordinate in the grid */
    private int row;

    /**
     * Constructs a new Coordinate object with the given column and row values.
     * 
     * @param x The column of the coordinate in the grid.
     * @param y The row of the coordinate in the grid.
     */
    public Coordinate(int x, int y) {
        this.col = x;
        this.row = y;
    }

    /**
     * Returns the column of the coordinate.
     * 
     * @return The column of the coordinate.
     */
    public int getColumn() {
        return col;
    }

    /**
     * Returns the row of the coordinate.
     * 
     * @return The row of the coordinate.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns a string representation of the coordinate in the format "column;row".
     * 
     * @return A string representation of the coordinate.
     */
    public String toString() {
        return getColumn() + ";" + getRow();
    }

    /**
     * Converts the given index to a Coordinate object.
     * 
     * @param index The index to be converted.
     * @return The Coordinate object that corresponds to the given index.
     */
    public static Coordinate convertToCoordinate(int index) {
        int count = 0;
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                if (x != 0 && y != 0 && x != 6 && y != 6)
                    continue;
                if (count == index)
                    return new Coordinate(x, y);
                count++;
            }
        }
        return new Coordinate(-1, -1); // there is something wrong with index
    }

    /**
     * Converts the given Coordinate object to an index.
     * 
     * @param coord The Coordinate object to be converted.
     * @return The index that corresponds to the given Coordinate object.
     */
    public static int convertToIndex(Coordinate coord) {
        int x = coord.getColumn();
        int y = coord.getRow();
        int index = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i != 0 && i != 6 && j != 0 && j != 6)
                    continue;
                if (j == x && i == y) {
                    return index;
                }
                index++;
            }
        }
        return -1;

    }
}
