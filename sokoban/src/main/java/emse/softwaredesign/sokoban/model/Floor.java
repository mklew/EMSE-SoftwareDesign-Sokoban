package emse.softwaredesign.sokoban.model;

import emse.softwaredesign.sokoban.view.SquareType;

/** Represents a Floor kind of block for the Sokoban game
 * @since 29/03/14
 */
public class Floor extends Block {

    /**
     * Indicates the presence of a storage on the block
     */
    private final boolean isStorage;

    /**
     * Indicates the presence of a box on the block
     */
    private boolean hasBox;

    /**
     * Constructor of a Floor kind of block
     *
     * @param position Position of the block in the level
     * @param storage true if the floor contains a storage
     */
    protected Floor (Position position, boolean storage) {
        super(position);
        isStorage = storage;
    }

    @Override public void addBox () {
        this.hasBox = true;
    }

    @Override public boolean canBeMovedOnto () {
        return !hasBox;
    }

    @Override public boolean canBeMovedOntoGiven (Block next) {
        if (next != null) {
            return (!hasBox || next.canBeMovedOnto());
        }
        return false;
    }

    @Override public void doMove (Block next) {
        if (this.hasBox) {
            this.hasBox = false;
            if (next != null) {
                next.addBox();
            }
        }
    }

    @Override public boolean isGameConditionSatisfied () {
        return (!isStorage || hasBox);
    }

    @Override public boolean isFloor () {
        return true;
    }

    @Override public boolean hasBox () {
        return hasBox;
    }

    @Override public boolean isLocation () {
        return isStorage;
    }

    @Override public SquareType getType() {
        SquareType type = SquareType.FLOOR;
        if (hasBox() && isLocation()) {
            type = SquareType.BOX_ON_THE_SLOT;
        }
        else if (isLocation()) {
            type = SquareType.BOX_SLOT;
        }
        else if (hasBox()) {
            type = SquareType.BOX;
        }
        return type;
    }
}
