package emse.softwaredesign.sokoban.controller;

import emse.softwaredesign.sokoban.model.Game;
import emse.softwaredesign.sokoban.model.MoveType;
import emse.softwaredesign.sokoban.view.BoardView;
import emse.softwaredesign.sokoban.view.View;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 01/05/14
 */
public class CLIController implements Controller {
    private View view;
    private Game game;

    @Override public void moveLeft () {
        game.move(MoveType.LEFT);
    }

    @Override public void moveRight () {
        game.move(MoveType.RIGHT);
    }

    @Override public void moveUp () {
        game.move(MoveType.UP);
    }

    @Override public void moveDown () {
        game.move(MoveType.DOWN);
    }

    @Override public BoardView getBoardView () {
        // TODO convert model to BoardView representation
        throw new NotImplementedException();
    }

    public void setView (View view) {
        this.view = view;
    }

    public View getView () {
        return view;
    }

    public void setGame (Game game) {
        this.game = game;
    }

    public Game getGame () {
        return game;
    }

    public void start () {
        game.initialize();
        view.show();
    }
}
