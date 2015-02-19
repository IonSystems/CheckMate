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
	public JPanel main;
	Controller controller;
	public Square[][] squares;
	public ArrayList<ChessPiece> whiteTaken;
	public ArrayList<ChessPiece> blackTaken;

	public int BOARDLENGTH;
	public Square[] selected;
	public int totalMoves;
	public Color firstSelected;

	public Board(Controller controller) {
		this.controller = controller;
		whiteTaken = new ArrayList<ChessPiece>();
        blackTaken = new ArrayList<ChessPiece>();
        main = new JPanel(new GridLayout(8, 8));
        main.setSize(1000,1000);
        main.setMaximumSize(new Dimension(1000,1000));
        main.setMinimumSize(new Dimension(1000,1000));
        squares = new Square[9][9];
        selected = new Square[2];
        
		BOARDLENGTH = 8;
		this.firstSelected = Color.WHITE;
		
		
        totalMoves = 0;
	}

	protected int checkMoveable(Square square, ChessPiece chessPiece) {
	        /*
	         * square is the square that has been clicked.
	         * selected[0] is the starting position of the piece.
	         * selected[1] is the finishing position of the piece.
	         */
			
	        if (selected[0] == null && selected[1] == null) {
	                if (square.getIcon() != null && square.getPiece().isPlayable() && controller.isTurn(square.getPiece().getTrueColour())) {
	                        firstSelected = square.getBackground();
	                        square.setBackground(Color.BLUE);
	                        highlightValidPositions(square.getPiece());
	                        selected[0] = square;
	                        return 0;
	                }
	        }
	        /*
	         * This should never happen, because as soon as the selected pieces are
	         * full, the move is made and the selected pieces set back to null
	         */
	        else if (selected[0] != null && selected[1] != null) {
	                movePiece(selected[0], selected[1],selected[0].getPiece().getMove(selected[1].getPosition()));
	                selected[0] = null;
	                selected[1] = null;
	                return 1;
	        } else if (selected[0] != null && selected[1] == null) {
	                if (true) {
	                        selected[1] = square;
	                        //Move move = selected[0].
	                        movePiece( selected[0], selected[1],selected[0].getPiece().getMove(selected[1].getPosition()));
	                        selected[0].setBackground(firstSelected);
	                        resetSquareColors();
	                        selected[0] = null;
	                        selected[1] = null;
	                        return 1;
	                }
	        }
	        return 1;
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
	        if(!controller.isTurn(from.getPiece().getTrueColour())){
	        	return false;
	        }
	        if (to.hasPiece() && from.hasPiece() && move.isTakeMove()) { //if there is a piece where we want to go.
	                valid = false;
	                return takePiece(from, to, move);
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
	                controller.nextPlayer();
	                checkPromotion(to.getPiece());
	        }
	        
	         
	        return true;
	}

	private void checkPromotion(ChessPiece piece) {
		int end = piece.getColour() ? 0 : 7;
		System.out.println("checking " + piece.getType() + " at " + piece.getPosition());
		if(piece.getType() == Piece.PAWN && piece.getPosition().getX() == end){
			System.out.println("PROMOTION");
			PawnSelectPane pane = new PawnSelectPane(this,(Pawn)piece);
			//promote(piece, type);
		}
		
	}

	boolean takePiece(Square from, Square to, Move move) {
	 
	        boolean valid = true;
	        //System.out.println("Taking:" + from.getPiece().getColour() + ", Taken:" + to.getPiece().getColour());
	        if (from.getPiece().getColour() == to.getPiece().getColour())
	                valid = false;// from.getPiece().getColour() ==
	                                                // to.getPiece().getColour()
	        if(!controller.isTurn(from.getPiece().getTrueColour()))
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
	                controller.nextPlayer();
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
	
	public void highlightValidPositions(ChessPiece piece) {
		for (Position p : piece.getValidPositions()) {
			squares[p.getX()][p.getY()].setBackground(Color.GREEN);
		}

	}
	public void resetSquareColors() {
		for (int i = 0; i < BOARDLENGTH; i++) {
			for (int j = 0; j < BOARDLENGTH; j++) {
				if (i != 8) {
					if ((i + j) % 2 == 0) {
						squares[i][j].setBackground(Color.WHITE);
					} else {
						squares[i][j].setBackground(Color.BLACK);
					}
				}
			}

		}
	}
	
	void setupPieces() {
		squares[7][0].addPiece(new ChessPiece(Piece.ROOK, true,
				new Position(7, 0), new ImageIcon("res/RookW.png"), this));
		squares[7][7].addPiece(new ChessPiece(Piece.ROOK, true,
				new Position(7, 7), new ImageIcon("res/RookW.png"), this));
		squares[0][7].addPiece(new ChessPiece(Piece.ROOK, false,
				new Position(0, 7), new ImageIcon("res/RookB.png"), this));
		squares[0][0].addPiece(new ChessPiece(Piece.ROOK, false,
				new Position(0, 0), new ImageIcon("res/RookB.png"), this));

		squares[7][1].addPiece(new ChessPiece(Piece.KNIGHT, true,
				new Position(7, 1), new ImageIcon("res/KnightW.png"), this));
		squares[7][6].addPiece(new ChessPiece(Piece.KNIGHT, true,
				new Position(7, 6), new ImageIcon("res/KnightW.png"), this));
		squares[0][6].addPiece(new ChessPiece(Piece.KNIGHT, false,
				new Position(0, 6), new ImageIcon("res/KnightB.png"), this));
		squares[0][1].addPiece(new ChessPiece(Piece.KNIGHT, false,
				new Position(0, 1), new ImageIcon("res/KnightB.png"), this));

		squares[7][2].addPiece(new ChessPiece(Piece.BISHOP, true,
				new Position(7, 2), new ImageIcon("res/BishopW.png"), this));
		squares[7][5].addPiece(new ChessPiece(Piece.BISHOP, true,
				new Position(7, 5), new ImageIcon("res/BishopW.png"), this));
		squares[0][5].addPiece(new ChessPiece(Piece.BISHOP, false,
				new Position(0, 5), new ImageIcon("res/BishopB.png"), this));
		squares[0][2].addPiece(new ChessPiece(Piece.BISHOP, false,
				new Position(0, 2), new ImageIcon("res/BishopB.png"), this));

		squares[7][3].addPiece(new ChessPiece(Piece.KING, true,
				new Position(7, 3), new ImageIcon("res/KingW.png"), this));
		squares[7][4].addPiece(new ChessPiece(Piece.QUEEN, true,
				new Position(7, 4), new ImageIcon("res/QueenW.png"), this));
		squares[0][3].addPiece(new ChessPiece(Piece.KING, false,
				new Position(0, 3), new ImageIcon("res/KingB.png"), this));
		squares[0][4].addPiece(new ChessPiece(Piece.QUEEN, false,
				new Position(0, 4), new ImageIcon("res/QueenB.png"), this));
		for (int i = 0; i <= 7; i++) {
			squares[6][i].addPiece(new Pawn(Piece.PAWN, true,
					new Position(6, i), new ImageIcon("res/PawnW.png"), this));
			squares[1][i].addPiece(new Pawn(Piece.PAWN, false,
					new Position(1, i), new ImageIcon("res/PawnB.png"), this));
		}
		
		

	}
	public void incrementTotalMoves() {
		totalMoves++;
	}

	public void promote(ChessPiece pawn, Piece type) {
		ChessPiece newPiece;
		switch(type){
		case ROOK:
			pawn.updateIcon(new ImageIcon("res/RookB.png"));
			
			
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
