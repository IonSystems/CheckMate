package testing;

import static org.junit.Assert.*;
import main.Board;
import main.Controller;
import main.Move;
import main.Position;
import main.Square;

import org.junit.Test;

import pieces.Piece;

public class PromotionTest {

	
	@Test
	public void promotionTest(){
		//Move 1 (White Move 1)
		Controller controller = new Controller();
		final Board board = controller.getGUI().getBoard();
		Position pTo = new Position(4,3);
		Position pFrom = new Position(6,3);
		Square from = board.getSquare(pFrom);
		Square to = board.getSquare(pTo);
		Move move = from.getPiece().getMove(to.getPosition());
		boolean moveSuccess = board.movePiece(from,to,move);
		sleep(1000);
		//Tests for Move 1
		assertTrue("Move 1 must be a success", moveSuccess);
		
		//Move 2 (Black Move 1)
		pTo = new Position(3,5);
		pFrom = new Position(1,5);
		to = board.getSquare(pTo);
		from = board.getSquare(pFrom);
		move = from.getPiece().getMove(to.getPosition());
		moveSuccess = board.movePiece(from,to,move);
		sleep(1000);
		// Tests for Move 2
		//assertTrue("Move 2 must be a success", moveSuccess);
		
		
		//Move 3 (White Move 2)
		pTo = new Position(3,3);
		pFrom = new Position(4,3);
		to = board.getSquare(pTo);
		from = board.getSquare(pFrom);
		move = from.getPiece().getMove(to.getPosition());
		moveSuccess = board.movePiece(from,to,move);
		sleep(1000);
		//Tests for Move 3
		assertTrue("Move 3 must be a success", moveSuccess);
				
		//System.out.println(board.getSquare(pTo));
		//assertEquals("There must be a pawn at the square moved to", Piece.PAWN, board.getSquare(pTo).getPiece().getType());
		//assertNull("There must be no piece at the square moved from", board.getSquare(pFrom).getPiece());
		
	}
private void sleep(int millis){
	try {
		Thread.sleep(millis);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
