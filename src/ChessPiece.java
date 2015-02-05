import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Create a chess piece with it's various attributes
 */
public class ChessPiece{
	ChessboardGUI board;
	ImageIcon image;
	int x;
	int y;
	Piece type;
	ArrayList<Move> validMoves;
	ArrayList<Position>validPositions;
	int points;
	
	//public ChessPiece(){};
	
	/*public ChessPiece(int initX, int initY, ImageIcon image,ChessboardGUI board){
		this.type = null;
		this.image = image;
		this.x = initX;
		this.y = initY;
	}*/
	
	public ChessPiece(Piece type, int initX, int initY, ImageIcon image, ChessboardGUI board){
		this.type = type;
		this.image = image;
		this.x = initX;
		this.y = initY;
		this.board = board;
		validMoves = new ArrayList<Move>();
		validPositions = new ArrayList<Position>();
		setupMoves();
		findValidPositions();
		
	}
	
	private void setupMoves(){
		switch(type){
			case KNIGHT:
				validMoves.add(new Move(2,1));
				validMoves.add(new Move(2,-1));
				validMoves.add(new Move(-2,1));
				validMoves.add(new Move(-2,-1));
				validMoves.add(new Move(1, 2));
				validMoves.add(new Move(1, -2));
				validMoves.add(new Move(-1,2));
				validMoves.add(new Move(-1,-2));
			break;
			
		}
	}
	public void findValidPositions(){
		for(Move m : validMoves){
			Position possiblePosition = new Position(x + m.getX(),y + m.getY());
			if(possiblePosition.getX() < 8 && possiblePosition.getX() >= 0 && possiblePosition.getY() < 8 && possiblePosition.getY() >= 0)
			if(!board.isOccupied(possiblePosition) && !validPositions.contains(possiblePosition)){
				validPositions.add(possiblePosition);
			}
		}
	}
	public void addMove(Move move){
		validMoves.add(move);
	}
	public void setPiece(Piece type){
		this.type = type;
	}
	public ImageIcon getIcon(){
		return this.image;
	}
	public Piece getType(){
		return type;
	}
	public void setPosition(Position position){
		this.x = position.getX();
		this.y = position.getY();
	}
	public ArrayList<Position> getValidPositions(){
		return validPositions;
	}
	
}
