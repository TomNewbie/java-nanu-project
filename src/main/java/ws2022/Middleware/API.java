package ws2022.Middleware;

public class API {
    public enum Type {
        ENTER_PROFILE, //
        DATA,
        TURN,
        ROLL_DICE, //
        ANSWER,
        SET_COLOR,
        END_GAME,
        UNKNOWN_TYPE,
        POP_UP,
        CHOOSE_COVER,
        CLOSE_CONNECTION
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

    public static void main(String[] args) {
        Type tho = getTypeFromClient("ENTER_PROFILE1;HEHE");
        System.out.println(tho.toString());
    }
}
