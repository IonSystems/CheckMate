package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import pieces.ChessPiece;
import pieces.Piece;

public class Board {
	public JPanel main;
	public Square[][] squares;
	public ArrayList<ChessPiece> whiteTaken;
	public ArrayList<ChessPiece> blackTaken;

	public int BOARDLENGTH;
	public Square[] selected;
	public int totalMoves;
	public Color firstSelected;

	public Board() {
		whiteTaken = new ArrayList<ChessPiece>();
        blackTaken = new ArrayList<ChessPiece>();
        main = new JPanel(new GridLayout(8, 8));
        squares = new Square[9][9];
        selected = new Square[2];
        
		BOARDLENGTH = 8;
		this.firstSelected = Color.WHITE;
		
		
        totalMoves = 0;
	}

	protected int checkMoveable(ChessboardGUI chessboardGUI, Square square, ChessPiece chessPiece) {
	        /*
	         * square is the square that has been clicked.
	         * selected[0] is the starting position of the piece.
	         * selected[1] is the finishing position of the piece.
	         */
	 
	        if (selected[0] == null && selected[1] == null) {
	                if (square.getIcon() != null && square.getPiece().isPlayable()) {
	                        firstSelected = square.getBackground();
	                        square.setBackground(Color.BLUE);
	                        chessboardGUI.highlightValidPositions(square.getPiece());
	                        selected[0] = square;
	                        return 0;
	                }
	        }
	        /*
	         * This should never happen, because as soon as the selected pieces are
	         * full, the move is made and the selected pieces set back to null
	         */
	        else if (selected[0] != null && selected[1] != null) {
	                chessboardGUI.board.movePiece(chessboardGUI, selected[0], selected[1],selected[0].getPiece().getMove(selected[1].getPosition()));
	                selected[0] = null;
	                selected[1] = null;
	                return 1;
	        } else if (selected[0] != null && selected[1] == null) {
	                if (true/* && rulesAdheredTo(selected[0], square) */) {
	                        selected[1] = square;
	                        //Move move = selected[0].
	                        chessboardGUI.board.movePiece(chessboardGUI, selected[0], selected[1],selected[0].getPiece().getMove(selected[1].getPosition()));
	                        selected[0].setBackground(firstSelected);
	                        chessboardGUI.resetSquareColors();
	                        selected[0] = null;
	                        selected[1] = null;
	                        return 1;
	                }
	        }
	        return 1;
	}

	boolean movePiece(ChessboardGUI chessboardGUI, Square from, Square to, Move move) {
	        //ChessPiece pieceBeingMoved = from.getPiece();
	        boolean valid = true;
	        if (from.getPiece() == null)
	                return false;
	        if (from.getPiece().getType() == null)
	                return false;
	        if(move == null)
	              return false;
	       
	        if (to.hasPiece() && from.hasPiece() && move.isTakeMove()) { //if there is a piece where we want to go.
	                valid = false;
	                return chessboardGUI.board.takePiece(chessboardGUI, from, to, move);
	                //Dead
	 
	        }if(to.getBackground() != Color.green) valid = false; //This seems a rather crude way of checking for a valid move.
	 
	        /*
	         * if (from.getIcon() == null || to.getIcon() != null) { return false; }
	         * else
	         */if (valid) {// Possible move
	                to.addPiece(from.getPiece());//Move the piece to the new empty square.
	                to.getPiece().setPosition(to.getPosition());//Update position of piece to the position of the new square
	                if ( to.getPiece().getType() == Piece.PAWN) to.getPiece().removeInitialMove();
	                to.getPiece().incrementMoves();
	                to.getPiece().findValidPositions();
	                from.addPiece(null);
	                return true;
	        }
	 
	        return false;
	}

	boolean takePiece(ChessboardGUI chessboardGUI, Square from, Square to, Move move) {
	 
	        boolean valid = true;
	        //System.out.println("Taking:" + from.getPiece().getColour() + ", Taken:" + to.getPiece().getColour());
	        if (from.getPiece().getColour() == to.getPiece().getColour())
	                valid = false;// from.getPiece().getColour() ==
	                                                // to.getPiece().getColour()
	        if (valid) {
	 
	                boolean takenColour = to.getPiece().getColour();
	                if (takenColour == true) {
	                        whiteTaken.add(to.getPiece());
	                        //Moving taken piece to the edge of the board
	                        //squares[7][whiteTaken.size()].addPiece(to.getPiece());
	                }
	                if (takenColour == false) {
	                        blackTaken.add(to.getPiece());
	                        //Moving taken piece to the edge of the board
	                        //squares[7][blackTaken.size()].addPiece(to.getPiece());
	                }
	                to.getPiece().setPlayable(false);
	                to.addPiece(from.getPiece());//Move the piece to the new empty square.
	                to.getPiece().setPosition(to.getPosition());//Update position of piece to the position of the new square
	                to.getPiece().findValidPositions();
	                from.addPiece(null);
	               
	                //System.out.println("Piece Taken!");
	        }
	        //System.out.println("Piece to be taken!");
	        return false;
	        // TODO Auto-generated method stub
	 
	}

	public Square getSquare(ChessboardGUI chessboardGUI, Position position) {
	        return squares[position.getX()][position.getY()];
	}
}