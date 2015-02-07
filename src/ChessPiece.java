import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Create a chess piece with it's various attributes
 */
public class ChessPiece {
	ChessboardGUI board;
	ImageIcon image;
	Position position;
	final int BOARD_DIMENSION = 8;
	Piece type;
	boolean whitePiece;
	ArrayList<Move> possibleMoves;
	ArrayList<Position> validPositions;
	int points;
	boolean playable; //True if still in the game, false if taken.

	// public ChessPiece(){};

	/*
	 * public ChessPiece(int initX, int initY, ImageIcon image,ChessboardGUI
	 * board){ this.type = null; this.image = image; this.x = initX; this.y =
	 * initY; }
	 */

	public ChessPiece(Piece type, boolean whitePiece,Position position, ImageIcon image,
			ChessboardGUI board) {
		this.type = type;
		this.image = image;
		this.position = position;
		this.whitePiece = whitePiece;
		this.board = board;
		possibleMoves = new ArrayList<Move>();
		validPositions = new ArrayList<Position>();
		setupMoves();
		findValidPositions();
		playable = true;

	}

	private void setupMoves() {
		switch (type) {
		case PAWN:
			if (whitePiece) {
				possibleMoves.add(new Move(-1, 0));
				possibleMoves.add(new Move(-1, 1));
				possibleMoves.add(new Move(-1, -1));
			} else {
				possibleMoves.add(new Move(1, 0));
				possibleMoves.add(new Move(1, 1));
				possibleMoves.add(new Move(1, -1));
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
		for (Move m : possibleMoves) {
			Position possiblePosition = new Position(position.getX() + m.getX(), position.getY() + m.getY());
			if (positionWithinBounds(possiblePosition) && noJumps(possiblePosition,m)){
				//if (!board.isOccupied(possiblePosition) 
					//	&& !validPositions.contains(possiblePosition)) {
					validPositions.add(possiblePosition);
				//}
			}
		}
	}

	private boolean noJumps(Position possiblePosition, Move possibleMove) {
		Position startPosition = position;
		Position endPosition = possiblePosition;
		System.out.println("Move: " + possibleMove);
		ArrayList<Square> squaresToCheck = getSquaresOnMove(possibleMove);
		//TODO: Complete this method.
		return true;
	}
	/**Get all the squares that are travelled upon during a move of a piece, not including the start and end squares.
	 * 
	 * @param possibleMove
	 * @return
	 */
	private ArrayList<Square> getSquaresOnMove(Move possibleMove) {
		ArrayList<Square> squares = new ArrayList<Square>();
		/*
		 * If the move x amount is positive, the move goes right, otherwise left.
		 * If the move y amount is positive, the move goes down, otherwise up.
		 */
		if(possibleMove.getX() > 0 && possibleMove.getY() > 0){
		for(int x = 0;x < possibleMove.getX(); x++){
			for(int y = 0;y < possibleMove.getY(); y++){
				Position t = addSquareToSquareArray(squares, x, y);
				System.out.println("Squares on move: " + t);
			}
			
		}
		}
		
		return null;
	}

	private Position addSquareToSquareArray(ArrayList<Square> squares, int x,
			int y) {
		Position t = new Position(position.getX() + x,position.getY() + y);
		squares.add(board.getSquare(t));
		return t;
	}

	private boolean positionWithinBounds(Position possiblePosition) {
		return possiblePosition.getX() < 8 && possiblePosition.getX() >= 0
				&& possiblePosition.getY() < 8
				&& possiblePosition.getY() >= 0;
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
		this.position = position;
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

	public String printValidPositions() {
		if(validPositions == null || validPositions.isEmpty()) return "No Valid Positions";
		return validPositions.toString();
		
	}

	public boolean isPlayable() {
		return playable;
	}

	public void setPlayable(boolean playable) {
		this.playable = playable;
		
	}

}
