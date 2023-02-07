package ws2022.Middleware;

/**
 * 
 * The API class represents the different types of messages that can be sent
 * between the client and server.
 * The API has an enumeration called Type that defines all the different message
 * types that can be sent.
 * The class also has a static method called getType that takes in a typeName
 * and returns the corresponding Type
 * enumeration value. The getTypeFromClient method takes in a message and
 * returns the Type of the message by
 * extracting the type string from the message.
 * 
 * @author
 * @version 1.0
 */
public class API {
    /**
     * The Type enumeration defines all the different message types that can be sent
     * between the client and server.
     */
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

    /**
     * The getType method takes in a typeName and returns the corresponding Type
     * enumeration value. If the typeName
     * does not match any of the enumeration values, it returns the UNKNOWN_TYPE
     * value.
     *
     * @param typeName the name of the type as a string
     * @return the corresponding Type enumeration value
     */
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

    /**
     * The getTypeFromClient method takes in a message and returns the Type of the
     * message by extracting the type
     * string from the message and calling the getType method.
     *
     * @param message the message to extract the Type from
     * @return the Type of the message
     */
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
