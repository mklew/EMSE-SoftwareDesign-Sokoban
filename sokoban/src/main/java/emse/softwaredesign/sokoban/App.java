package emse.softwaredesign.sokoban;

import emse.softwaredesign.sokoban.controller.ViewController;
import emse.softwaredesign.sokoban.model.Game;
import emse.softwaredesign.sokoban.view.SokobanGame;

/**
 * Main entry point to application. Initialises MVC structure
 * @since 01/05/14
 */
public class App {

    public static void main (String[] args) {
        final Game game = new Game();

        final ViewController viewController = new ViewController();

        final SokobanGame view = new SokobanGame();

        viewController.setView(view);
        viewController.setGame(game);
        view.setController(viewController);

        viewController.start();
    }
}
