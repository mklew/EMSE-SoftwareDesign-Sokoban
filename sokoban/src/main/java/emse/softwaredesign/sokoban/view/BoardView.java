package emse.softwaredesign.sokoban.view;

import emse.softwaredesign.sokoban.model.Position;

import java.util.Map;

/**
 * Container for 2D array of squares.
 * @since 01/05/14
 */
public final class BoardView {

    private final Map<Position, SquareTypes> posToSquareType;

    public BoardView (Map<Position, SquareTypes> posToSquareType) {
        this.posToSquareType = posToSquareType;
    }

    SquareTypes getType (int x, int y) {
        final Position position = new Position(x, y);
        final SquareTypes squareTypes = posToSquareType.get(position);
        if (squareTypes == null) {
            throw new RuntimeException("Should exist in the map " + position);
        }
        return squareTypes;
    }
}
