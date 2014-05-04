/**
 * 
 */
package emse.softwaredesign.sokoban.view;

/**
 * @author Alberth Montero <alberthm@gmail.com>
 *
 */
public class Piece {
	// Instance Variables
		SquareTypes color;
		int posX;
		int posY;

		/**
		 * Default Constructor of a Piece Object
		 */
		public Piece() {
			color = SquareTypes.OUTSIDE_WALL;
			posX = 0;
			posY = 0;
		}// end of default constructor

		/**
		 * Method to check if is the opponent color
		 * 
		 * @param piece the piece in turn
		 * @return True if is the opponent piece
		 */

		/**
		 * Method to put a piece on the board
		 * 
		 * @param x point
		 * @param y point
		 */
		public void setPos(int x, int y) {
			posX = x;
			posY = y;
		}// end of setPos(x,y)
	}// end of Piece Class