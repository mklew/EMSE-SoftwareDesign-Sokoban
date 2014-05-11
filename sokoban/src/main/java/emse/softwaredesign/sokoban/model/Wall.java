package emse.softwaredesign.sokoban.model;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public class Wall extends Block {
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
