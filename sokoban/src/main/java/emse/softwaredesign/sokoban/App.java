package emse.softwaredesign.sokoban;

import emse.softwaredesign.sokoban.controller.CLIController;
import emse.softwaredesign.sokoban.model.Game;
import emse.softwaredesign.sokoban.view.CLIView;

/**
 * Main entry point to application. Initialises MVC structure
 *
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 01/05/14
 */
public class App {

    public static void main (String[] args) {
        final Game game = new Game();

        final CLIController cliController = new CLIController();

        final CLIView cliView = new CLIView();

        cliController.setView(cliView);
        cliController.setGame(game);
        cliView.setController(cliController);

        cliController.start();
    }
}
