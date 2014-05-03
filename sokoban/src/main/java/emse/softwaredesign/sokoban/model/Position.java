package emse.softwaredesign.sokoban.model;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @author Jérémy Bossut
 * @since 29/03/14
 */
public class Position {

    private final int row;

    private final int col;

    public Position (int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position left() {
        return new Position(row,(col-1));
    }

    public Position right() {
        return new Position(row,(col+1));
    }

    public Position up() {
        return new Position((row-1),col);
    }

    public Position down() {
        return new Position((row+1),col);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Position)) {
            Position p = (Position) obj;
            return (this.row == p.getRow() && this.col == p.getCol());
        }
        return false;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
