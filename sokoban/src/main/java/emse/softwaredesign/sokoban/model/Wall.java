package emse.softwaredesign.sokoban.model;

/** Represents a Wall kind of block for the Sokoban game
 * @since 29/03/14
 */
public class Wall extends Block {
    /**
     * Constructor of a Wall kind of block
     *
     * @param position Position of the block in the level
     */
    public Wall (Position position) {
        super(position);
    }

    @Override public void addBox () {
        throw new IllegalStateException();
    }

    @Override public boolean canBeMovedOnto () {
        return false;
    }

    @Override public boolean canBeMovedOntoGiven (Block next) {
        return false;
    }

    @Override public void doMove (Block next) {
        throw new IllegalStateException();
    }

    @Override public boolean isGameConditionSatisfied () {
        return true;
    }

    @Override public boolean isFloor () {
        return false;
    }

    @Override public boolean hasBox () {
        return false;
    }

    @Override public boolean isLocation () {
        return false;
    }

}
