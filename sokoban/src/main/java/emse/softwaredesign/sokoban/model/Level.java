package emse.softwaredesign.sokoban.model;

import java.util.Map;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 05/05/14
 */
public final class Level {
    private final Position playerPosition;

    private final Map<Position, Block> blocks;

    public Level (Position playerPosition,
                  Map<Position, Block> blocks) {
        this.playerPosition = playerPosition;
        this.blocks = blocks;
    }

    public Position getPlayerPosition () {
        return playerPosition;
    }

    public Map<Position, Block> getBlocks () {
        return blocks;
    }
}
