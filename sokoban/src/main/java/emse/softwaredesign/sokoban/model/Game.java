package emse.softwaredesign.sokoban.model;

import java.util.List;
import java.util.Map;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public class Game {

    private Position playerPosition;

    private List<List<Block>> blocks;

    private Map<Position, Block> blocksMap;

    public void move (MoveType moveType) {
        Block newBlock;
        Block nextBlock;
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
            default:
                newBlock = null;
                nextBlock = null;
        }
        if (newBlock != null && (newBlock.canBeMovedOnto() || newBlock.canBeMovedOntoGiven(nextBlock))) {
            newBlock.doMove(nextBlock);
            this.playerPosition = newBlock.getPosition();
        }
    }

    private Block getBlockAt (Position position) {
        for (List<Block> row : blocks) {
            for (Block b : row) {
                if (b.getPosition().equals(position)) {
                    return b;
                }
            }
        }
        return new Wall(position);
    }


    public boolean isGameFinished () {
        for (List<Block> row : blocks) {
            for (Block b : row) {
                if (!(b.isGameConditionSatisfied())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void initialize () {
        final Level level = LevelParser.parseLevel(levelText);
        playerPosition = level.getPlayerPosition();
        blocksMap = level.getBlocks();
    }

    // TODO need to prepare a levelText
    private final static String levelText =
            "- - W W W W W - \n" +
                    "W W W f f f W - \n" +
                    "W l p b f f W - \n" +
                    "W W W f b l W - \n" +
                    "W l W W b f W - \n" +
                    "W b W f l f W W \n" +
                    "W f f f f b l W \n" +
                    "W W W W W W W W";
}
