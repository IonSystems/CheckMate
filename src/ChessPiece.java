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
				possibleMoves.add(new Move(-1, 0,false,true)); //Can only move to empty square
				possibleMoves.add(new Move(-1, 1,true,false)); //Can only take in this move
				possibleMoves.add(new Move(-1, -1,true,false));
			} else {
				possibleMoves.add(new Move(1, 0,false,true));
				possibleMoves.add(new Move(1, 1,true,false));
				possibleMoves.add(new Move(1, -1,true,false));
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
		//System.out.println("Move: " + possibleMove);
		ArrayList<Square> squaresToCheck = new ArrayList<Square>();
		squaresToCheck = getSquaresOnMove(possibleMove);
		try{
		if(squaresToCheck.isEmpty()) return true; //If there are no squares in the way of the move, there are no jumps.
		}catch(NullPointerException e){
			return true;
		}
		for(Square s : squaresToCheck){
			if(s.getPiece() != null){ //if there is a piece in the way of a move
				//Do not allow move
				System.out.println("Disbaled Move(" + type + "from " + this.position + " to " + possiblePosition );
				return false;
			}
		}
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
		int x = possibleMove.getX();
		int y = possibleMove.getY();
		/*
		 * If the move x amount is positive, the move goes right, otherwise left.
		 * If the move y amount is positive, the move goes down, otherwise up.
		 * 
		 * There are no squares 'squares on move' for pawns, because they can only move by one.
		 * The <b>bishop</b> can move diagonally by any amount as long as the diagonal route is unobstruced.
		 * The rook can move to any non-obstructed square with in it's row or column.
		 * The queen combines the moves of the rook and the bishop, so can move across it's row, column and diagonally.
		 * The king can move one square in any direction, but cannot move to a square resulting in check.
		 */
		
		switch(type){
		case KING:
			
		break;
		case QUEEN:
			
		break;
		case ROOK:
			
		break;
		case BISHOP://Diagonal, so movement in x and y direction will be equal magnitude.
			if(x > 0 && y > 0){//The move is going diagonally down to the right.
				for(int i = 1;i < x;i++){
					Position positionOnMove = new Position(x+i,y+i);
					if(positionOnMove.inBounds()) squares.add(board.getSquare(positionOnMove));
				}				
			}else if(x < 0 && y < 0){ //The move is going diagonally up to the left.
				for(int i = -1;i < x;i--){
					Position positionOnMove = new Position(x+i,y+i);
					if(positionOnMove.inBounds()) squares.add(board.getSquare(positionOnMove));
				}
			}
			if(!squares.isEmpty()) System.out.println("Bishop move inbetweeners." + squares);
		break;
		case KNIGHT:
			
		break;
		case PAWN://No squares between start and end positions of the piece, so we return an empty array.
			return squares;
		
		}
//		if(possibleMove.getX() > 0 && possibleMove.getY() > 0){
//		for(int x = 0;x < possibleMove.getX(); x++){
//			for(int y = 0;y < possibleMove.getY(); y++){
//				Position t = addSquareToSquareArray(squares, x, y);
//				System.out.println("Squares on move: " + t);
//			}
//			
//		}
//		}
		
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
	public Move getMove(Position endPosition){
		for(Move m : possibleMoves){
			if(position.getX() + m.getX() == endPosition.getX() && position.getY() + m.getY() == endPosition.getY()){
				return m;
			}
		}
		return null;
	}

}
