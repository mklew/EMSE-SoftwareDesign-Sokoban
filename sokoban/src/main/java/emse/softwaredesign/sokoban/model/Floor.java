package emse.softwaredesign.sokoban.model;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public class Floor extends Block {

    private final boolean isStorage;

    private boolean hasBox;

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
        return !isStorage || isStorage && hasBox;
    }
}
