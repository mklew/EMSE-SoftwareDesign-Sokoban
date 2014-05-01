package emse.softwaredesign.sokoban.model;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public class Wall extends Block {
    @Override public void addBox () {
        throw new IllegalStateException();
    }

    @Override public boolean canBeMovedOnto () {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override public boolean canBeMovedOntoGiven (Block next) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override public void doMove (Block next) {
        throw new IllegalStateException();
    }

    @Override public boolean isGameConditionSatisfied () {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
