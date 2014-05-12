package emse.softwaredesign.sokoban.controller;

import emse.softwaredesign.sokoban.view.BoardView;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 01/05/14
 */
public interface Controller {

    void moveLeft ();

    void moveRight ();

    void moveUp ();

    void moveDown ();

    /**
     * @return boardView, representation of the model
     */
    BoardView getBoardView ();


    void start ();

    boolean isGameFinished ();
}
