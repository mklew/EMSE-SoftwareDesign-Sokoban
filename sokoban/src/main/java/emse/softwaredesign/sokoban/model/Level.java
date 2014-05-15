package emse.softwaredesign.sokoban.model;

import java.util.Map;

/** Represents a level of Sokoban
 * @since 05/05/14
 */
public final class Level {
    /**
     * Initial position of the player
     */
    private final Position playerPosition;
    /**
     * Every block of the level with its position
     */
    private final Map<Position, Block> blocks;
    /**
     * Number of rows in the level
     */
    private final int rows;
    /**
     * Number of columns in the level
     */
    private final int cols;

    /**
     * Constructor of a level of Sokoban
     *
     * @param playerPosition Current position of the player
     * @param blocks Every block of the level with its position
     */
    public Level (Position playerPosition,
                  Map<Position, Block> blocks,
                  int rows,
                  int cols) {
        this.playerPosition = playerPosition;
        this.blocks = blocks;
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Get the position of the player in the level
     *
     * @return current position of the player
     */
    public Position getPlayerPosition () {
        return playerPosition;
    }

    /**
     * Get the blocks of the level with their position
     *
     * @return map matching every block of the level with their position
     */
    public Map<Position, Block> getBlocks () {
        return blocks;
    }

    /**
     * Get the number of rows in the level
     *
     * @return number of rows in the level
     */
    public int getRows () {
        return rows;
    }

    /**
     * Get the number of columns in the level
     *
     * @return number of columns in the level
     */
    public int getColumns () {
        return cols;
    }
}
