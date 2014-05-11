package emse.softwaredesign.sokoban.model;

import java.util.Map;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public class Game {

    private Position playerPosition;

    private Map<Position, Block> blocksMap;

    public void move (MoveType moveType) {
        Block newBlock = null;
        Block nextBlock = null;
        switch (moveType) {
            case LEFT:
                newBlock = this.getBlockAt(playerPosition.left());
                nextBlock = this.getBlockAt(playerPosition.left().left());
                break;
            case RIGHT:
                newBlock = this.getBlockAt(playerPosition.right());
                nextBlock = this.getBlockAt(playerPosition.right().right());
                break;
            case UP:
                newBlock = this.getBlockAt(playerPosition.up());
                nextBlock = this.getBlockAt(playerPosition.up().up());
                break;
            case DOWN:
                newBlock = this.getBlockAt(playerPosition.down());
                nextBlock = this.getBlockAt(playerPosition.down().down());
                break;
        }
        if (newBlock != null && (newBlock.canBeMovedOnto() || newBlock.canBeMovedOntoGiven(nextBlock))) {
            newBlock.doMove(nextBlock);
            this.playerPosition = newBlock.getPosition();

        }
    }

    /**
     * Used to retrieve blocks from the model. Non existent blocks are substituted by walls
     *
     * @param position
     * @return block, never null
     */
    Block getBlockAt (Position position) {
        final Block block = blocksMap.get(position);
        if (block != null) {
            return block;
        } else {
            return new Wall(position);
        }
    }

    /**
     * Used to retrieve block from blocks map. Can return null values
     *
     * @param position
     * @return block or null
     */
    public Block getBlockFromBlocks(Position position) {
        return blocksMap.get(position);
    }

    public int getRows () {
        return 8;
    }

    public int getColumns () {
        return 8;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }


    public boolean isGameFinished () {
        for (Map.Entry<Position, Block> blockEntry : blocksMap.entrySet()) {
            if (!blockEntry.getValue().isGameConditionSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public void initialize () {
        final Level level = LevelParser.parseLevel(levelText);
        playerPosition = level.getPlayerPosition();
        blocksMap = level.getBlocks();
    }

    private final static String levelText =
            "- - W W W W W - \n" +
                    "W W W f f f W - \n" +
                    "W L P B f f W - \n" +
                    "W W W f B L W - \n" +
                    "W L W W B f W - \n" +
                    "W B W f L f W W \n" +
                    "W f f f f B L W \n" +
                    "W W W W W W W W ";
}
