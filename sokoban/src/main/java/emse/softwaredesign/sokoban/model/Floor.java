package emse.softwaredesign.sokoban.model;

/** Represents a Floor kind of block for the Sokoban game
 * @since 29/03/14
 */
public class Floor extends Block {

    /**
     * Indicates the presence of a storage on the block
     */
    private final boolean isStorage;

    /**
     * Indicates the presence of a green point on the block
     */
    private final boolean isGreenPoint;

    /**
     * Indicates the presence of a box on the block
     */
    private boolean hasBox;

    /**
     * Indicates the presence of a dog on the block
     */
    private boolean hasDog;

    /**
     * Constructor of a Floor kind of block
     *
     * @param position Position of the block in the level
     * @param storage true if the floor contains a storage
     */
    protected Floor (Position position, boolean storage) {
        super(position);
        isStorage = storage;
        isGreenPoint = false;
    }

    /**
     * Constructor of a Floor kind of block
     *
     * @param position Position of the block in the level
     * @param storage true if the floor contains a storage
     * @param greenPoint true if the floor contains a green point
     */
    protected Floor (Position position, boolean storage, boolean greenPoint) {
        super(position);
        isStorage = storage;
        isGreenPoint = greenPoint && !storage;
    }

    @Override public void addBox () {
        this.hasBox = true;
    }

    @Override public void addDog () {
        this.hasDog = true;
    }

    @Override public boolean canBeMovedOnto () {
        return !(hasBox || hasDog);
    }

    @Override public boolean canBeMovedOntoGiven (Block next) {
        if (next != null) {
            return ((!hasBox || (!isLocation() && next.canBeMovedOnto())) && (!hasDog || (!next.isLocation() && next.canBeMovedOnto())));
        }
        return false;
    }

    @Override public void doMove (Block next) {
        if (this.hasBox) {
            this.hasBox = false;
            if (next != null && !next.isGreenPoint()) {
                next.addBox();
            }
        }
        if (this.hasDog) {
            this.hasDog = false;
            if (next != null) {
                next.addDog();
            }
        }
    }

    @Override public boolean isGameConditionSatisfied () {
        return (!isStorage || hasBox);
    }

    @Override public boolean isFloor () {
        return true;
    }

    @Override public boolean isLocation () {
        return isStorage;
    }

    @Override public boolean isGreenPoint () {
        return isGreenPoint;
    }

    @Override public SquareType getType() {
        SquareType type = SquareType.FLOOR;
        if (hasBox && isStorage) {
            type = SquareType.BOX_ON_THE_SLOT;
        }
        else if (isStorage) {
            type = SquareType.BOX_SLOT;
        }
        else if (hasBox) {
            type = SquareType.BOX;
        }
        else if (hasDog) {
            type = SquareType.DOG;
        }
        else if (isGreenPoint) {
            type = SquareType.GREEN_POINT;
        }
        return type;
    }
}
