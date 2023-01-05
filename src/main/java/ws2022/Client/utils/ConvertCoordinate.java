package ws2022.Client.utils;

import ws2022.Client.Model.Coordinate;

public class ConvertCoordinate {
    public static Coordinate convertFromIndex(int index) {
        int x = index % 6;
        int y = (index - x) / 6;
        return new Coordinate(x, y);
    }
}
