/**
 * @author Cameron A. Craig Move defines an individual move for a chess piece.
 *         For example a Knight can move 2 forwards and one left. This move will
 *         be represented as forward = 2, left = 1, backward = 0, right = 0;
 */
class Move {
	private int x;
	private int y;

	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
