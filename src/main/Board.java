package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import pieces.ChessPiece;
import pieces.Pawn;
import pieces.Piece;

public class Board {
	
	
	public Square[][] squares;
	public ArrayList<ChessPiece> whiteTaken;
	public ArrayList<ChessPiece> blackTaken;

	public int BOARDLENGTH;
	
	public int totalMoves;
	public Color firstSelected;

	public Board() {
		
		whiteTaken = new ArrayList<ChessPiece>();
        blackTaken = new ArrayList<ChessPiece>();
        squares = new Square[9][9];
       
        
		BOARDLENGTH = 8;
		this.firstSelected = Color.WHITE;
		
		
        totalMoves = 0;
	}

	

	public boolean movePiece(Square from, Square to, Move move) {
	        //ChessPiece pieceBeingMoved = from.getPiece();
	        boolean valid = true;
	        if (from.getPiece() == null)
	                return false;
	        if (from.getPiece().getType() == null)
	                return false;
	        if(move == null)
	              return false;
	        if(!Controller.getInstance().isTurn(from.getPiece().getColour())){
	        	return false;
	        }
	        if (to.hasPiece() && from.hasPiece() && move.isTakeMove()) { //if there is a piece where we want to go.
	                valid = false;
	                return takePiece(from, to, move);
	                //Dead
	 
	        }//if(to.getBackground() != Color.green) valid = false; //This seems a rather crude way of checking for a valid move.
	 
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
	                Controller.getInstance().nextPlayer();
	                checkPromotion(to.getPiece());
	        }
	        
	         
	        return true;
	}

	private void checkPromotion(ChessPiece piece) {
		int end = piece.getColour() ? 0 : 7;
		System.out.println("checking " + piece.getType() + " at " + piece.getPosition());
		if(piece.getType() == Piece.PAWN && piece.getPosition().getX() == end){
			System.out.println("PROMOTION");
			
			//promote(piece, type);
		}
		
	}

	boolean takePiece(Square from, Square to, Move move) {
	 
	        boolean valid = true;
	        //System.out.println("Taking:" + from.getPiece().getColour() + ", Taken:" + to.getPiece().getColour());
	        if (from.getPiece().getColour() == to.getPiece().getColour())
	                valid = false;// from.getPiece().getColour() ==
	                                                // to.getPiece().getColour()
	        if(!Controller.getInstance().isTurn(from.getPiece().getColour()))
	        	valid = false;
	        	
	        if(!move.isTakeMove())
	        	valid = false;
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
	                Controller.getInstance().nextPlayer();
	                
	                 checkPromotion(to.getPiece());
	                //System.out.println("Piece Taken!");
	        }
	      
	        //System.out.println("Piece to be taken!");
	        return true;
	        // TODO Auto-generated method stub
	        
	}

	public Square getSquare(Position position) {
	        return squares[position.getX()][position.getY()];
	}
	
	

	
	
	
	void setupPieces() {
		squares[7][0].addPiece(new ChessPiece(Piece.ROOK, true,
				new Position(7, 0)));
		squares[7][7].addPiece(new ChessPiece(Piece.ROOK, true,
				new Position(7, 7)));
		squares[0][7].addPiece(new ChessPiece(Piece.ROOK, false,
				new Position(0, 7)));
		squares[0][0].addPiece(new ChessPiece(Piece.ROOK, false,
				new Position(0, 0)));

		squares[7][1].addPiece(new ChessPiece(Piece.KNIGHT, true,
				new Position(7, 1)));
		squares[7][6].addPiece(new ChessPiece(Piece.KNIGHT, true,
				new Position(7, 6)));
		squares[0][6].addPiece(new ChessPiece(Piece.KNIGHT, false,
				new Position(0, 6)));
		squares[0][1].addPiece(new ChessPiece(Piece.KNIGHT, false,
				new Position(0, 1)));

		squares[7][2].addPiece(new ChessPiece(Piece.BISHOP, true,
				new Position(7, 2)));
		squares[7][5].addPiece(new ChessPiece(Piece.BISHOP, true,
				new Position(7, 5)));
		squares[0][5].addPiece(new ChessPiece(Piece.BISHOP, false,
				new Position(0, 5)));
		squares[0][2].addPiece(new ChessPiece(Piece.BISHOP, false,
				new Position(0, 2)));

		squares[7][3].addPiece(new ChessPiece(Piece.KING, true,
				new Position(7, 3)));
		squares[7][4].addPiece(new ChessPiece(Piece.QUEEN, true,
				new Position(7, 4)));
		squares[0][3].addPiece(new ChessPiece(Piece.KING, false,
				new Position(0, 3)));
		squares[0][4].addPiece(new ChessPiece(Piece.QUEEN, false,
				new Position(0, 4)));
		for (int i = 0; i <= 7; i++) {
			squares[6][i].addPiece(new Pawn(Piece.PAWN, true,
					new Position(6, i), new ImageIcon("res/PawnW.png"), this));
			squares[1][i].addPiece(new Pawn(Piece.PAWN, false,
					new Position(1, i), new ImageIcon("res/PawnB.png"), this));
		}
		System.out.println("Board set up");
		

	}
	public void incrementTotalMoves() {
		totalMoves++;
	}

	public void promote(ChessPiece pawn, Piece type) {
		ChessPiece newPiece;
		switch(type){
		case ROOK:
		
			
			
			break;
		case KING:
			break;
		case QUEEN:
			break;
		case KNIGHT:
			break;
		case BISHOP:
			break;
		
		}
		
	}
	

}
