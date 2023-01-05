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

}
