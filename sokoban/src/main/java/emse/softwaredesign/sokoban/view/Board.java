/**
 * 
 */
package emse.softwaredesign.sokoban.view;

/**
 * @author Alberth Montero <alberthm@gmail.com>
 *
 */
public class Board extends Observable {
	// Instance Variables
	final int ROWS = 8;
	final int COLS = 8;
	private Piece[][] board;
	private SquareTypes turn;
	SquareTypes winner;
	int darkPieces;
	int lightPieces;

	/**
	 * Default constructor that create a new Board.
	 */
	public Board() {
		// Create board
		board = new Piece[COLS][];
		for (int y = 0; y < 8; y++)
			board[y] = new Piece[ROWS];
		// Initialize board
		for (int y = 0; y < ROWS; y++)
			for (int x = 0; x < COLS; x++)
				board[y][x] = new Piece();
		// Set first player
		turn = SquareTypes.FLOOR;
		winner = SquareTypes.OUTSIDE_WALL;
	}// end default constructor

	/**
	 * Runnable method startGame which start the Sokoban game.
	 */
	public void startGame() {
		System.out.println("Board.startGame()");
		// Empty the board
		emptyBoard();

		// Set initial States
		//turn = SquareTypes.FLOOR;
		//winner = SquareTypes.OUTSIDE_WALL;
		board[0][2].color = SquareTypes.WALL;
		board[0][3].color = SquareTypes.WALL;
		board[0][4].color = SquareTypes.WALL;
		board[0][5].color = SquareTypes.WALL;
		board[0][6].color = SquareTypes.WALL;
		board[1][0].color = SquareTypes.WALL;
		board[1][1].color = SquareTypes.WALL;
		board[1][2].color = SquareTypes.WALL;
		board[1][3].color = SquareTypes.FLOOR;
		board[1][4].color = SquareTypes.FLOOR;
		board[1][5].color = SquareTypes.FLOOR;
		board[1][6].color = SquareTypes.WALL;
		board[2][0].color = SquareTypes.WALL;
		board[2][1].color = SquareTypes.BOX_ON_LOCATION;
		board[2][2].color = SquareTypes.BOX;
		board[2][2].color = SquareTypes.PLAYER;
		board[2][3].color = SquareTypes.BOX;
		board[2][4].color = SquareTypes.FLOOR;
		board[2][5].color = SquareTypes.FLOOR;
		board[2][6].color = SquareTypes.WALL;
		board[3][0].color = SquareTypes.WALL;
		board[3][1].color = SquareTypes.WALL;
		board[3][2].color = SquareTypes.WALL;
		board[3][3].color = SquareTypes.FLOOR;
		board[3][4].color = SquareTypes.BOX;
		board[3][5].color = SquareTypes.BOX_ON_LOCATION;
		board[3][6].color = SquareTypes.WALL;
		board[4][0].color = SquareTypes.WALL;
		board[4][1].color = SquareTypes.BOX_ON_LOCATION;
		board[4][2].color = SquareTypes.WALL;
		board[4][3].color = SquareTypes.WALL;
		board[4][4].color = SquareTypes.BOX;
		board[4][5].color = SquareTypes.FLOOR;
		board[4][6].color = SquareTypes.WALL;
		board[5][0].color = SquareTypes.WALL;
		board[5][1].color = SquareTypes.BOX;
		board[5][2].color = SquareTypes.WALL;
		board[5][3].color = SquareTypes.FLOOR;
		board[5][4].color = SquareTypes.BOX_ON_LOCATION;
		board[5][5].color = SquareTypes.FLOOR;
		board[5][6].color = SquareTypes.WALL;
		board[5][7].color = SquareTypes.WALL;
		board[6][0].color = SquareTypes.WALL;
		board[6][1].color = SquareTypes.FLOOR;
		board[6][2].color = SquareTypes.FLOOR;
		board[6][3].color = SquareTypes.FLOOR;
		board[6][4].color = SquareTypes.FLOOR;
		board[6][5].color = SquareTypes.BOX;
		board[6][6].color = SquareTypes.BOX_ON_LOCATION;
		board[6][7].color = SquareTypes.WALL;
		board[7][0].color = SquareTypes.WALL;
		board[7][1].color = SquareTypes.WALL;
		board[7][2].color = SquareTypes.WALL;
		board[7][3].color = SquareTypes.WALL;
		board[7][4].color = SquareTypes.WALL;
		board[7][5].color = SquareTypes.WALL;
		board[7][6].color = SquareTypes.WALL;
		board[7][7].color = SquareTypes.WALL;
		System.out.println(toString());
		//calculateNextPossibleMoves();
		notifyObservers();
	}// end of startGame()

	/**
	 * Method that cleans the board
	 */
	private void emptyBoard() {
		for (int y = 0; y < ROWS; y++)
			for (int x = 0; x < COLS; x++)
				board[y][x].color = SquareTypes.OUTSIDE_WALL;
	}// end of emptyBoard()

	/**
	 * Method that return a Piece object from the board
	 * 
	 * @param x point
	 * @param y point
	 * @return a piece position on the board
	 */
	public Piece get(int x, int y) {
		return board[y][x];
	}// end of get(x,y)

	/**
	 * Method to print results in the command line
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(turn + "\n");
		sb.append(" 0 1 2 3 4 5 6 7");
		for (int x = 0; x < COLS; x++) {
			sb.append("\n" + x + " ");
			for (int y = 0; y < ROWS; y++) {
				sb.append(board[x][y].color + " ");
			}
		}
		sb.append("\n");
		return sb.toString();
	}// end of toString()
}// end of Board Class
