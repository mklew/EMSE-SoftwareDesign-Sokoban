package emse.softwaredesign.sokoban.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/** Contains the game logic of the Sokoban game
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
     * Current level
     */
    private Level level;
    /**
     * Levels of the game
     */
    private LinkedList<Level> gameLevels;

    private Iterator<Level> iterator;

    public Game () {
        this.gameLevels = new LinkedList<Level>();
        this.gameLevels.add(LevelParser.parseLevel(levelText));
        this.gameLevels.add(LevelParser.parseLevel(levelText3));
        this.gameLevels.add(LevelParser.parseLevel(levelText4));
        this.gameLevels.add(LevelParser.parseLevel(levelText2));
        this.iterator = this.gameLevels.iterator();
        this.level = this.iterator.next();
    }

    /**
     * Performs the move
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
     * @param position Position of the block
     * @return block or null
     */
    public Block getBlockFromBlocks(Position position) {
        return blocksMap.get(position);
    }

    /**
     * Gets the number of rows in the level
     * @return number of rows in the level
     */
    public int getRows () {
        return level.getRows();
    }

    /**
     * Gets the number of columns in the level
     * @return number of columns in the level
     */
    public int getColumns () {
        return level.getColumns();
    }

    /**
     * Gets the position of the player in the level
     * @return current position of the player
     */
    public Position getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Informs if the level is finished by checking that game conditions have been met
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
     * Informs if the current level is not the last of the game
     * @return true if the current level is not the last of the game, false otherwise
     */
    public boolean hasNextLevel () {
        return this.iterator.hasNext();
    }

    /**
     * Changes the current level to the next
     */
    public void goToNextLevel () {
        if (iterator.hasNext()) {
            this.level = iterator.next();
        }
    }

    /**
     * Initializes the game
     */
    public void initialize () {
        playerPosition = level.getPlayerPosition();
        blocksMap = level.getBlocks();
    }

    /**
     * Example of a level to be parsed
     */
    private final static String levelText =
            "- - W W W W W - \n" +
            "W W W f f f W - \n" +
            "W L P d f f W - \n" +
            "W W W f B L W - \n" +
            "W L W W B f W - \n" +
            "W B W f L f W W \n" +
            "W f f f f B L W \n" +
            "W W W W W W W W ";

    /**
     * Example 2 of a level to be parsed
     */
    private final static String levelText2 =
            "- - - - W W W W W - - - - - - - - - - \n" +
            "- - - - W f f f W - - - - - - - - - - \n" +
            "- - - - W B f f W - - - - - - - - - - \n" +
            "- - W W W f f B W W - - - - - - - - - \n" +
            "- - W f f B f B f W - - - - - - - - - \n" +
            "W W W f W f W W f W - - - W W W W W W \n" +
            "W f f f W f W W f W W W W W f f L L W \n" +
            "W f B f f B f f f f f f f f f f L L W \n" +
            "W W W W W f W W W f W P W W f f L L W \n" +
            "- - - - W f f f f f W W W W W W W W W \n" +
            "- - - - W W W W W W W - - - - - - - - ";

    /**
     * Example 3 of a level to be parsed
     */
    private final static String levelText3 =
            "- W W W W W - \n" +
            "W W L L f W W \n" +
            "W f f B f f W \n" +
            "W W B B f W W \n" +
            "W f f B f P W \n" +
            "W W L L f W W \n" +
            "- W W W W W - ";
    /**
     * Example 4 of a level to be parsed
     */
    private final static String levelText4 =
            "- - W W W W - - - \n" +
            "W W W f f W W W W \n" +
            "W f f f B P f f W \n" +
            "W f W f f W B f W \n" +
            "W f L f L W f f W \n" +
            "W W W W W W W W W ";
}