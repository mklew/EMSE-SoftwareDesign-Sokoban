package emse.softwaredesign.sokoban.controller;

import emse.softwaredesign.sokoban.model.Block;
import emse.softwaredesign.sokoban.model.Game;
import emse.softwaredesign.sokoban.model.MoveType;
import emse.softwaredesign.sokoban.model.Position;
import emse.softwaredesign.sokoban.view.BoardView;
import emse.softwaredesign.sokoban.view.SquareTypes;
import emse.softwaredesign.sokoban.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 01/05/14
 */
public class ViewController implements Controller {
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
        Map<Position, SquareTypes> posToType = new HashMap<>();

        for (int x = 0; x < game.getRows(); x++) {
            for (int y = 0; y < game.getColumns(); y++) {
                final Position position = new Position(x, y);
                final Block blockAt = game.getBlockFromBlocks(position);
                if (blockAt == null) {
                    posToType.put(position, SquareTypes.OUTSIDE_WALL);
                } else {
                    if (!blockAt.isFloor()) {
                        posToType.put(position, SquareTypes.WALL);
                    } else if (blockAt.isFloor() && blockAt.hasBox() && blockAt.isLocation()) {
                        posToType.put(position, SquareTypes.BOX_ON_THE_SLOT);
                    } else if (blockAt.isFloor() && blockAt.isLocation()) {
                        posToType.put(position, SquareTypes.BOX_SLOT);
                    } else if (blockAt.isFloor() && blockAt.hasBox()) {
                        posToType.put(position, SquareTypes.BOX);
                    } else if (blockAt.isFloor()) {
                        posToType.put(position, SquareTypes.FLOOR);
                    } else {
                        throw new IllegalStateException("Unexpected");
                    }

                }

            }
        }
        posToType.put(game.getPlayerPosition(), SquareTypes.PLAYER);
        return new BoardView(posToType);
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

    @Override public void start () {
        game.initialize();
        view.show();
    }
}
