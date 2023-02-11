package ws2022;

/**
 * The Starter class serves as an entry point for starting the JavaFX
 * application.
 * 
 * It invokes the main method of the App class.
 *
 * This class was introduced as a solution to a problem encountered when
 * building the project into a JAR file.
 * 
 * The problem was related to missing JavaFX runtime components, as described in
 * the following Stack Overflow post:
 * https://stackoverflow.com/questions/56894627/how-to-fix-error-javafx-runtime-components-are-missing-and-are-required-to-run
 */
public class Starter {
    public static void main(final String[] args) {
        App.main(args);
    }
}
