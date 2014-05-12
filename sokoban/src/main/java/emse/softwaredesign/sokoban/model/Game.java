package emse.softwaredesign.sokoban.model;

import java.util.Map;

/** Contains the game logic of the Sokoban game
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @author Jérémy Bossut
 * @since 29/03/14
 */
public class Game {

    /**
     * Current position of the player
     */
    private Position playerPosition;
    /**
     * Every block of the level with its position
     */
    private Map<Position, Block> blocksMap;

    /**
     * Perform the move
     *
     * @param moveType the type of move asked (LEFT, RIGHT, UP or DOWN)
     */
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
     * @param position Position of the block
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
     * @param position Position of the block
     * @return block or null
     */
    public Block getBlockFromBlocks(Position position) {
        return blocksMap.get(position);
    }

    /**
     * Get the number of rows in the level
     *
     * @return number of rows in the level
     */
    public int getRows () {
        return 8;
    }

    /**
     * Get the number of columns in the level
     *
     * @return number of columns in the level
     */
    public int getColumns () {
        return 8;
    }

    /**
     * Get the position of the player in the level
     *
     * @return current position of the player
     */
    public Position getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Inform if the level is finished
     *
     * @return true if the level is completed, false otherwise
     */
    public boolean isGameFinished () {
        for (Map.Entry<Position, Block> blockEntry : blocksMap.entrySet()) {
            if (!blockEntry.getValue().isGameConditionSatisfied()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Initialize the game by parsing a text level
     */
    public void initialize () {
        final Level level = LevelParser.parseLevel(levelText);
        playerPosition = level.getPlayerPosition();
        blocksMap = level.getBlocks();
    }

    /**
     * Example of a level to be parsed
     */
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
