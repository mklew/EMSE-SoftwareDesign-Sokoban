package emse.softwaredesign.sokoban;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public class Floor extends Block {

    private final boolean isStorage;

    private boolean hasBox;

    public Floor (boolean storage) {
        isStorage = storage;
    }

    @Override public void addBox () {
        this.hasBox = true;
    }

    @Override public boolean canBeMovedOnto () {
        return hasBox;
    }

    @Override public boolean canBeMovedOntoGiven (Block next) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override public void doMove (Block next) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override public boolean isGameConditionSatisfied () {
        return !isStorage || isStorage && hasBox;
    }

    @Override public void draw () {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
