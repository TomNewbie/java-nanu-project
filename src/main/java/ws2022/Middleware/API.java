package ws2022.Middleware;

public class API {
    public enum Type {
        ENTER_PROFILE, //
        READY,
        UN_READY,
        ROLL_DICE, //
        GUESS_PICTURE,
        RIGHT_ANSWER,
        WRONG_ANSWER,
        CHOOSE_COLOR,
        SET_COLOR,
        END_GAME,
        UNKNOWN_TYPE,
    }

    // public static
    public static Type getType(String typeName) {
        Type result = Type.UNKNOWN_TYPE;
        try {
            result = Enum.valueOf(Type.class, typeName);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Unknown Type: " + e.getMessage());
        }
        return result;
    }

    public static Type getTypeFromClient(String message) {
        String typeString = message.split(";")[0];
        Type result = getType(typeString);
        return result;
    }
}