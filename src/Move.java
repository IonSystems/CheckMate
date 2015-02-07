/**
 * @author Cameron A. Craig
 * 
 *         Move defines an individual move for a chess piece. For example a
 *         Knight can move 2 forwards and one left. This move will be
 *         represented as forward = 2, left = 1, backward = 0, right = 0;
 *         
 *         Moves in two dimensions can be executed in two different ways, adding complications for us when it comes to checking whether a move is obstructed by another piece or not.
 *         
 */
class Move {
	private int x;
	private int y;

	public Move(int x, int y) {
		this.x = x;
		this.y = y;
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
	public String toString(){
		return "Move x:" + x + " Move y:" + y ;
	}
}
