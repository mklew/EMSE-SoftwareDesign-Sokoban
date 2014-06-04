package emse.softwaredesign.sokoban.view;

import emse.softwaredesign.sokoban.model.Position;

import java.util.Map;

/**
 * Container for 2D array of squares.
 * @since 01/05/14
 */
public final class BoardView {

    private final Map<Position, SquareType> posToSquareType;

    public BoardView (Map<Position, SquareType> posToSquareType) {
        this.posToSquareType = posToSquareType;
    }

    SquareType getType (int x, int y) {
        final Position position = new Position(x, y);
        final SquareType squareType = posToSquareType.get(position);
        if (squareType == null) {
            throw new RuntimeException("Should exist in the map " + position);
        }
        return squareType;
    }
}
