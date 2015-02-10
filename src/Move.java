/**
 * @author Cameron A. Craig
 * 
 *         Move defines an individual move for a chess piece. For example a
 *         Knight can move 2 forwards and one left. 
 * 
 *         Moves in two dimensions can be executed in two different ways, adding
 *         complications for us when it comes to checking whether a move is
 *         obstructed by another piece or not.
 * 
 */
class Move {
	private int x;
	private int y;
	private boolean takeMove; // Moving a piece to an occupied square.
	private boolean normalMove; // A move to an empty square.
	private boolean firstMoveOnly; // If this is true the move is only valid on
									// the first move of the piece.

	public Move(int x, int y) { // For normal moves that are are both take moves
								// and non-take moves, and do not apply just on
								// first move.
		this.x = x;
		this.y = y;
		this.takeMove = true;
		this.normalMove = true;
		this.firstMoveOnly = false;
	}

	public Move(int x, int y, boolean firstMoveOnly) {
		this.x = x;
		this.y = y;
		this.takeMove = true;
		this.normalMove = true;
		this.firstMoveOnly = firstMoveOnly;
	}

	public Move(int x, int y, boolean takeMove, boolean normalMove) {
		this.x = x;
		this.y = y;
		this.takeMove = takeMove;
		this.normalMove = normalMove;
		this.firstMoveOnly = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "Move x:" + x + " Move y:" + y;
	}

	public boolean isNormalMove() {
		return normalMove;
	}

	public boolean isTakeMove() {
		return takeMove;

	}

	public boolean isFirstMove() {
		return firstMoveOnly;
	}
}
