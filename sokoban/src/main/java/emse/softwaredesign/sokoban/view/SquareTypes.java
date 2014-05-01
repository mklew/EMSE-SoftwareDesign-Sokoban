package emse.softwaredesign.sokoban.view;

/**
 * This are all possible combinations of squares. Each View should know how to represent them. This indirection allows views to know nothing about real model.
 * <p/>
 * This is called <i>Fake Model</i>
 *
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 01/05/14
 */
public enum SquareTypes {
    PLAYER, BOX, BOX_ON_LOCATION, PLAYER_ON_LOCATION, FLOOR, WALL, OUTSIDE_WALL
}
