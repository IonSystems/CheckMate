import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.Position;

/**
 * Create a chess piece with it's various attributes
 */
public class ChessPiece{
	ChessboardGUI board;
	ImageIcon image;
	int x;
	int y;
	final int BOARD_DIMENSION = 8;
	Piece type;
	boolean whitePiece;
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
		whitePiece = true;
		this.board = board;
		validMoves = new ArrayList<Move>();
		validPositions = new ArrayList<Position>();
		setupMoves();
		findValidPositions();
		
	}
	
	private void setupMoves(){
		switch(type){
			case PAWN:
				if (whitePiece){
				validMoves.add(new Move(0,-1));
				validMoves.add(new Move(1, -1));
				validMoves.add(new Move(-1,-1));
				}
				else {
				validMoves.add(new Move (0,1));
				validMoves.add(new Move (1,1));
				validMoves.add(new Move (-1,1));
				}
			break;
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
			case BISHOP:
				for (int i = 0; i < BOARD_DIMENSION; i++){
					validMoves.add(new Move(i,i));
					validMoves.add(new Move(i,-i));
					validMoves.add(new Move(-i,i));
					validMoves.add(new Move(-i,-i));
				}
			break;
			case ROOK:
				for (int i = 0; i < BOARD_DIMENSION; i ++){
					validMoves.add(new Move(0,i));
					validMoves.add(new Move(i,0));
					validMoves.add(new Move(0,-i));
					validMoves.add(new Move(-i,0));
				}
			case QUEEN:
				for (int i = 0; i < BOARD_DIMENSION; i ++){
					validMoves.add(new Move(0,i));
					validMoves.add(new Move(i,0));
					validMoves.add(new Move(0,-i));
					validMoves.add(new Move(-i,0));
					validMoves.add(new Move(i,i));
					validMoves.add(new Move(i,-i));
					validMoves.add(new Move(-i,i));
					validMoves.add(new Move(-i,-i));
				}
			break;
			case KING:
				validMoves.add(new Move(0,1));
				validMoves.add(new Move(0,-1));
				validMoves.add(new Move(1,1));
				validMoves.add(new Move(1,-1));
				validMoves.add(new Move(-1,1));
				validMoves.add(new Move(-1,-1));
				validMoves.add(new Move(1,0));
				validMoves.add(new Move(-1,0));
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
