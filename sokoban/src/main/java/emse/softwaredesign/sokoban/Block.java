package emse.softwaredesign.sokoban;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public abstract class Block {
    private Position position;

    public abstract void addBox ();

    public abstract boolean canBeMovedOnto ();

    public abstract boolean canBeMovedOntoGiven (Block next);

    public abstract void doMove (Block next);

    public abstract boolean isGameConditionSatisfied ();

}
