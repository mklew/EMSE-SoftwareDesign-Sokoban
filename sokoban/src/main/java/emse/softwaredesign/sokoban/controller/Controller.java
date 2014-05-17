package emse.softwaredesign.sokoban.controller;

import emse.softwaredesign.sokoban.model.Game;
import emse.softwaredesign.sokoban.view.BoardView;

/**
 * @since 01/05/14
 */
public interface Controller {

    /** Performs a move to the left
     */
    void moveLeft ();
    /** Performs a move to the right
     */
    void moveRight ();
    /** Performs a move up
     */
    void moveUp ();
    /** Performs a move down
     */
    void moveDown ();

    /** Gets the representation of the model
     * @return boardView, representation of the model
     */
    BoardView getBoardView ();

    /** Starts the game
     */
    void start ();

    /**
     * Informs if the game is finished
     *
     * @return true if the game is finished, false otherwise
     */
    boolean isGameFinished ();

    /**
     * Gets the number of rows in the level
     *
     * @return number of rows in the level
     */
    int getRows ();
    /**
     * Gets the number of columns in the level
     *
     * @return number of columns in the level
     */
    int getColumns ();

    /**
     * Sets the game that the controller controls
     *
     * @param game the game controlled by the controller
     */
    public void setGame (Game game);
}
