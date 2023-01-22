package ws2022.Client.Model;

public class Coordinate {
    private int col;
    private int row;

    public Coordinate(int x, int y) {
        this.col = x;
        this.row = y;
    }

    public int getColumn() {
        return col;
    }

    public int getRow() {
        return row;
    }

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
