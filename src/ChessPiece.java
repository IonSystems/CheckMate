import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Create a chess piece with it's various attributes
 */
public class ChessPiece{

	ImageIcon image;
	int x;
	int y;
	Piece type;
	ArrayList<Move> validMoves;
	int points;
	
	public ChessPiece(){};
	
	public ChessPiece(int initX, int initY, ImageIcon image){
		this.type = null;
		this.image = image;
		this.x = initX;
		this.y = initY;
	}
	
	public ChessPiece(Piece type, int initX, int initY, ImageIcon image){
		this.type = type;
		this.image = image;
		this.x = initX;
		this.y = initY;
		validMoves = new ArrayList<Move>();
		setupMoves();
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
	
}
