package testing;

import static org.junit.Assert.*;
import main.Board;

import org.junit.Test;

public class MovementTest {
Board board = new Board();
board.setupPieces();
	
	public void testPawnMovement(){
		board.movePiece(null, null, null);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
