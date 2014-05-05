package emse.softwaredesign.sokoban.model;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public abstract class Block {
    private Position position;

    protected Block (Position position) {
        this.position = position;
    }

    public abstract void addBox ();

    public abstract boolean canBeMovedOnto ();

    public abstract boolean canBeMovedOntoGiven (Block next);

    public abstract void doMove (Block next);

    public abstract boolean isGameConditionSatisfied ();

    public Position getPosition() {
        return position;
    }

}
