package emse.softwaredesign.sokoban.model;

import java.util.Map;

/** Represents a level of Sokoban
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @author Jérémy Bossut
 * @since 05/05/14
 */
public final class Level {
    /**
     * Current position of the player
     */
    private final Position playerPosition;
    /**
     * Every block of the level with its position
     */
    private final Map<Position, Block> blocks;

    /**
     * Constructor of a level of Sokoban
     *
     * @param playerPosition Current position of the player
     * @param blocks Every block of the level with its position
     */
    public Level (Position playerPosition,
                  Map<Position, Block> blocks) {
        this.playerPosition = playerPosition;
        this.blocks = blocks;
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
}
