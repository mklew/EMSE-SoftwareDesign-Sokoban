package emse.softwaredesign.sokoban.view;

import emse.softwaredesign.sokoban.controller.ViewController;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 01/05/14
 */
public class CLIView implements View {

    private ViewController controller;

    @Override
    public void show () {
        final BoardView boardView = controller.getBoardView();

        // TODO use boardView to draw stuff.

        // TODO wait for user input and call appropriate controller method, then redraw itself.
    }

    public void setController (ViewController controller) {
        this.controller = controller;
    }

    public ViewController getController () {
        return controller;
    }
}
