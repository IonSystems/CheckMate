import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Create a chess piece with it's various attributes
 */
public class ChessPiece {
	ChessboardGUI board;
	ImageIcon image;
	int x;
	int y;
	final int BOARD_DIMENSION = 8;
	Piece type;
	boolean whitePiece;
	ArrayList<Move> possibleMoves;
	ArrayList<Position> validPositions;
	int points;

	// public ChessPiece(){};

	/*
	 * public ChessPiece(int initX, int initY, ImageIcon image,ChessboardGUI
	 * board){ this.type = null; this.image = image; this.x = initX; this.y =
	 * initY; }
	 */

	public ChessPiece(Piece type, int initX, int initY, ImageIcon image,
			ChessboardGUI board) {
		this.type = type;
		this.image = image;
		this.x = initX;
		this.y = initY;
		whitePiece = true;
		this.board = board;
		possibleMoves = new ArrayList<Move>();
		validPositions = new ArrayList<Position>();
		setupMoves();
		findValidPositions();

	}

	private void setupMoves() {
		switch (type) {
		case PAWN:
			if (whitePiece) {
				possibleMoves.add(new Move(0, -1));
				possibleMoves.add(new Move(1, -1));
				possibleMoves.add(new Move(-1, -1));
			} else {
				possibleMoves.add(new Move(0, 1));
				possibleMoves.add(new Move(1, 1));
				possibleMoves.add(new Move(-1, 1));
			}
			break;
		case KNIGHT:
			possibleMoves.add(new Move(2, 1));
			possibleMoves.add(new Move(2, -1));
			possibleMoves.add(new Move(-2, 1));
			possibleMoves.add(new Move(-2, -1));
			possibleMoves.add(new Move(1, 2));
			possibleMoves.add(new Move(1, -2));
			possibleMoves.add(new Move(-1, 2));
			possibleMoves.add(new Move(-1, -2));
			break;
		case BISHOP:
			for (int i = 1; i < BOARD_DIMENSION; i++) {
				possibleMoves.add(new Move(i, i));
				possibleMoves.add(new Move(i, -i));
				possibleMoves.add(new Move(-i, i));
				possibleMoves.add(new Move(-i, -i));
			}
			break;
		case ROOK:
			for (int i = 1; i < BOARD_DIMENSION; i++) {
				possibleMoves.add(new Move(0, i));
				possibleMoves.add(new Move(i, 0));
				possibleMoves.add(new Move(0, -i));
				possibleMoves.add(new Move(-i, 0));
			}
		break;
		case QUEEN:
			for (int i = 1; i < BOARD_DIMENSION; i++) {
				possibleMoves.add(new Move(0, i));
				possibleMoves.add(new Move(i, 0));
				possibleMoves.add(new Move(0, -i));
				possibleMoves.add(new Move(-i, 0));
				possibleMoves.add(new Move(i, i));
				possibleMoves.add(new Move(i, -i));
				possibleMoves.add(new Move(-i, i));
				possibleMoves.add(new Move(-i, -i));
			}
			break;
		case KING:
			possibleMoves.add(new Move(0, 1));
			possibleMoves.add(new Move(1, 0));
			possibleMoves.add(new Move(0, -1));
			possibleMoves.add(new Move(-1, 0));
			possibleMoves.add(new Move(1, 1));
			possibleMoves.add(new Move(1, -1));
			possibleMoves.add(new Move(-1, 1));
			possibleMoves.add(new Move(-1, -1));
			break;
		}
	}

	public void findValidPositions() {
		validPositions.clear();
		//
		System.out.println(type + " #:" + possibleMoves.size()+ possibleMoves.toString());
		for (Move m : possibleMoves) {
			System.out.println(m + ", ");
			Position possiblePosition = new Position(x + m.getX(), y + m.getY());
			if (possiblePosition.getX() < 8 && possiblePosition.getX() >= 0
					&& possiblePosition.getY() < 8
					&& possiblePosition.getY() >= 0)
				if (!board.isOccupied(possiblePosition) 
						&& !validPositions.contains(possiblePosition)) {
					validPositions.add(possiblePosition);
				}
		}
	}

	public void addMove(Move move) {
		possibleMoves.add(move);
	}

	public void setPiece(Piece type) {
		this.type = type;
	}

	public ImageIcon getIcon() {
		return this.image;
	}

	public Piece getType() {
		return type;
	}

	public void setPosition(Position position) {
		this.x = position.getX();
		this.y = position.getY();
	}

	public ArrayList<Position> getValidPositions() {
		return validPositions;
	}

	public boolean getColour() {
		return whitePiece;
	}

	public String toString(){
		return ""+ type;
	}

}
