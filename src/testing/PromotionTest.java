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
	public void ClickTest(){
		Controller controller = new Controller();
		final Board board = controller.getGUI().getBoard();
		sleep(1000);
		move(board,6,3,4,3); //white pawn
		sleep(1000);
		move(board,1,5,3,5); //black pawn
		sleep(1000);
		move(board,4,3,3,3); //white pawn
		sleep(1000);
		move(board,3,5,4,5); //black pawn
		sleep(1000);
		move(board,3,3,2,3); //white pawn
		sleep(1000);
		move(board,4,5,5,5); //black pawn
		sleep(1000);
		move(board,2,3,1,4); //white pawn
		sleep(1000);
		move(board,5,5,6,6); //black pawn
		sleep(1000);
		move(board,1,4,0,3); //white pawn ready for promotion
		sleep(1000);
		move(board,6,6,7,5); //black pawn ready for promotion
		sleep(1000);
		
	}
	private void move(Board board,int x1, int y1, int x2, int y2){
		board.clearClicks();
		board.click(x1,y1);
		board.click(x2,y2);
	}
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
		
		//Tests for Move 1
		
		assertTrue("Move 1 must be a success", moveSuccess);
		sleep(1000);
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
