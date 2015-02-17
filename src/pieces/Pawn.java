package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import main.Board;
import main.ChessboardGUI;
import main.Move;
import main.Position;
import main.Square;

public class Pawn extends ChessPiece{
	
	//firstMoveWhite 
	//firstMoveBlack = 
	public Pawn(Piece type, boolean whitePiece, Position position,
			ImageIcon image, Board board){
		
		super(type,whitePiece,position,image,board);
	
		
	}
	
	public void removeInitialMove(){
		possibleMoves.remove(firstMoveBlack);
		possibleMoves.remove(firstMoveWhite);
	}
	

	
	
	protected void setupMoves() {
			if (whitePiece) {
				possibleMoves.add(new Move(-1, 0, false, true)); // Can only
																	// move to
																	// empty
																	// square
				possibleMoves.add(new Move(-1, 1, true, false)); // Can only
																	// take in
																	// this move
				possibleMoves.add(new Move(-1, -1, true, false));
				possibleMoves.add(firstMoveWhite);
				
			} else {
				possibleMoves.add(new Move(1, 0, false, true));
				possibleMoves.add(new Move(1, 1, true, false));
				possibleMoves.add(new Move(1, -1, true, false));
				
				possibleMoves.add(firstMoveBlack);
			}
			
	}
	
	protected ArrayList<Square> getSquaresOnMove(Move possibleMove) {
		return new ArrayList<Square>();
	}
	protected boolean noJumps(Position possiblePosition, Move possibleMove) {
		return true;
	}
	public void removeFirstMove() {
		possibleMoves.remove(firstMoveBlack);
		possibleMoves.remove(firstMoveWhite);
	}


	
}
