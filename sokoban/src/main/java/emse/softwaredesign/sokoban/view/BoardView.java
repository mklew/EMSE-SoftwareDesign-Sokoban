package emse.softwaredesign.sokoban.view;

import java.util.List;

/**
 * Container for 2D array of squares.
 *
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 01/05/14
 */
public final class BoardView {

    /**
     * First list is for rows, inner list is for columns
     */
    private final List<List<SquareTypes>> board;

    public BoardView (List<List<SquareTypes>> board) {
        this.board = board;
    }

    public List<List<SquareTypes>> getBoard () {
        return board;
    }
}
