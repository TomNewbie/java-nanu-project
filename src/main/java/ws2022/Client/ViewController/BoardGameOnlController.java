package ws2022.Client.ViewController;

public class BoardGameOnlController {
    private static BoardGameOnlController bgoc;

    private BoardGameOnlController() {
    }

    public static BoardGameOnlController getInstance() {
        if (BoardGameOnlController.bgoc == null) {
            bgoc = new BoardGameOnlController();
        }
        return bgoc;
    }

    private SceneController sc = SceneController.getInstance();
}
