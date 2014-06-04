package emse.softwaredesign.sokoban.model;

/** Represents a block for the Sokoban game
 * @since 29/03/14
 */
public abstract class Block {
    /**
     * Block position
     */
    private Position position;

    /**
     * Constructor of a block
     *
     * @param position Position of the block in the level
     */
    protected Block (Position position) {
        this.position = position;
    }

    /**
     * Adds a box on the block
     */
    public abstract void addBox ();

    /**
     * Informs if the block can host the player
     *
     * @return true if the block can host the player, false otherwise
     */
    public abstract boolean canBeMovedOnto ();

    /**
     * Informs if the block can host the player knowing that the next block will be affected
     *
     * @param next Block also affected by the move
     * @return true if the block can host the player, false otherwise
     */
    public abstract boolean canBeMovedOntoGiven (Block next);

    /**
     * Triggers the move on that block. May result in transfer of the box from this block to the next.
     *
     * @param next Block also affected by the move
     */
    public abstract void doMove (Block next);

    /**
     * Returns true if game conditions have been met
     *
     * @return true if game conditions have been met, false otherwise
     */
    public abstract boolean isGameConditionSatisfied ();

    /**
     * Informs if the block is a Floor
     *
     * @return true if the block is a Floor, false otherwise
     */
    public abstract boolean isFloor();

    /**
     * Informs if the block has a box
     *
     * @return true if the block has a box, false otherwise
     */
    public abstract boolean hasBox();

    /**
     * Informs if the block is a location spot
     *
     * @return true if the block is a location spot, false otherwise
     */
    public abstract boolean isLocation();

    /**
     * Get the position of the block
     *
     * @return position of the block
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Get the type of the block
     *
     * @return type of the block
     */
    public abstract SquareType getType();
}
