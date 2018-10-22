/**
 * Created by Yuliia Tesliuk on 10/21/2018
 */
public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        controller.game();
    }
}
