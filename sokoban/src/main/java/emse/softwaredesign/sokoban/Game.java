package emse.softwaredesign.sokoban;

import java.util.List;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 29/03/14
 */
public class Game implements Drawable {

    private Position playerPosition;

    private List<List<Block>> blocks;

    public void move (MoveType moveType) {

    }
//
//    private Block getBlockAt (Position position) {
//        return null;
//    }

    @Override
    public void draw () {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isGameFinished () {
        return false;
    }
}
