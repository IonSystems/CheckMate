import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//GUI for showing the chess AI

public class ChessboardGUI extends JFrame implements ActionListener {

	JPanel main;
	Square[][] squares;
	ArrayList<ChessPiece> whiteTaken;
	ArrayList<ChessPiece> blackTaken;
	JMenuBar menuBar;
	JMenu options, help, file;
	JMenuItem save, load, exit, difficulty, sound, volume, helpPage,
			onlineHelp;
	final int BOARDLENGTH = 8;
	Square[] selected;
	int difficultyLevel = 0;
	int volumeLevel = 50;
	boolean soundOn = true;

	public ChessboardGUI() {
		whiteTaken = new ArrayList<ChessPiece>();
		blackTaken = new ArrayList<ChessPiece>();
		main = new JPanel(new GridLayout(8, 8));
		squares = new Square[9][9];
		selected = new Square[2];
		setUp();
		setupPieces();
		menuBar = new JMenuBar();
		options = new JMenu("Options");
		help = new JMenu("Help");
		file = new JMenu("File");
		save = new JMenuItem("Open");
		save.addActionListener(this);
		load = new JMenuItem("Load");
		load.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		difficulty = new JMenuItem("Difficulty");
		difficulty.addActionListener(this);
		sound = new JMenuItem("Sound On/Off");
		sound.addActionListener(this);
		volume = new JMenuItem("Volume");
		volume.addActionListener(this);
		helpPage = new JMenuItem("Help Page");
		helpPage.addActionListener(this);
		onlineHelp = new JMenuItem("Online Help");
		onlineHelp.addActionListener(this);
		file.add(save);
		file.add(load);
		file.add(exit);
		options.add(difficulty);
		options.add(sound);
		options.add(volume);
		help.add(helpPage);
		help.add(onlineHelp);
		menuBar.add(file);
		menuBar.add(options);
		menuBar.add(help);
		menuBar.setVisible(true);
		this.add(main);
		setJMenuBar(menuBar);
		this.setResizable(true);
		this.setSize(1000, 1000);
		this.add(main);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	Color firstSelected = null;

	protected int checkMoveable(Square square, ChessPiece chessPiece) {
		/*
		 * square is the square that has been clicked.
		 * selected[0] is the starting position of the piece.
		 * selected[1] is the finishing position of the piece.
		 */

		if (selected[0] == null && selected[1] == null) {
			if (square.getIcon() != null && square.getPiece().isPlayable()) {
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
			if (true/* && rulesAdheredTo(selected[0], square) */) {
				selected[1] = square;
				//Move move = selected[0].
				movePiece(selected[0], selected[1],selected[0].getPiece().getMove(selected[1].getPosition()));
				selected[0].setBackground(firstSelected);
				resetSquareColors();
				selected[0] = null;
				selected[1] = null;
				return 1;
			} 
		}
		return 1;
	}

	private void resetSquareColors() {

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

	private void highlightValidPositions(ChessPiece piece) {
		for (Position p : piece.getValidPositions()) {
			squares[p.getX()][p.getY()].setBackground(Color.GREEN);
		}

	}

	private boolean movePiece(Square from, Square to, Move move) {
		//ChessPiece pieceBeingMoved = from.getPiece();
		boolean valid = true;
		if (from.getPiece() == null)
			return false;
		if (from.getPiece().getType() == null)
			return false;
		
		if (to.getPiece() != null) { //if there is a piece where we want to go.
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
			to.getPiece().findValidPositions();
			from.addPiece(null);
			return true;
		}

		return false;
	}

	private boolean takePiece(Square from, Square to,Move move) {

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

	//private boolean rulesAdheredTo(Square to, Square from) {
		//return (to.getPiece().getValidPositions().contains(from.getPosition()));
	//}

	private void setupPieces() {
		squares[7][0].addPiece(new ChessPiece(Piece.ROOK,true,new Position( 7, 0), new ImageIcon(
				"res/RookW.png"), this));
		squares[7][7].addPiece(new ChessPiece(Piece.ROOK,true, new Position(7, 7), new ImageIcon(
				"res/RookW.png"), this));
		squares[0][7].addPiece(new ChessPiece(Piece.ROOK,false, new Position(0, 7), new ImageIcon(
				"res/RookB.png"), this));
		squares[0][0].addPiece(new ChessPiece(Piece.ROOK,false, new Position(0, 0), new ImageIcon(
				"res/RookB.png"), this));

		squares[7][1].addPiece(new ChessPiece(Piece.KNIGHT,true, new Position(7, 1),
				new ImageIcon("res/KnightW.png"), this));
		squares[7][6].addPiece(new ChessPiece(Piece.KNIGHT,true, new Position(7, 6),
				new ImageIcon("res/KnightW.png"), this));
		squares[0][6].addPiece(new ChessPiece(Piece.KNIGHT,false, new Position(0, 6),
				new ImageIcon("res/KnightB.png"), this));
		squares[0][1].addPiece(new ChessPiece(Piece.KNIGHT,false, new Position(0, 1),
				new ImageIcon("res/KnightB.png"), this));

		squares[7][2].addPiece(new ChessPiece(Piece.BISHOP,true, new Position(7, 2),
				new ImageIcon("res/BishopW.png"), this));
		squares[7][5].addPiece(new ChessPiece(Piece.BISHOP,true, new Position(7, 5),
				new ImageIcon("res/BishopW.png"), this));
		squares[0][5].addPiece(new ChessPiece(Piece.BISHOP,false, new Position(0, 5),
				new ImageIcon("res/BishopB.png"), this));
		squares[0][2].addPiece(new ChessPiece(Piece.BISHOP,false, new Position(0, 2),
				new ImageIcon("res/BishopB.png"), this));

		squares[7][3].addPiece(new ChessPiece(Piece.KING,true, new Position(7, 3), new ImageIcon(
				"res/KingW.png"), this));
		squares[7][4].addPiece(new ChessPiece(Piece.QUEEN,true, new Position(7, 4), new ImageIcon(
				"res/QueenW.png"), this));
		squares[0][3].addPiece(new ChessPiece(Piece.KING,false, new Position(0, 3), new ImageIcon(
				"res/KingB.png"), this));
		squares[0][4].addPiece(new ChessPiece(Piece.QUEEN,false, new Position(0, 4), new ImageIcon(
				"res/QueenB.png"), this));
		for (int i = 0; i <= 7; i++) {
			squares[6][i].addPiece(new ChessPiece(Piece.PAWN,true, new Position(6, i),
					new ImageIcon("res/PawnW.png"), this));
			squares[1][i].addPiece(new ChessPiece(Piece.PAWN,false, new Position(1, i),
					new ImageIcon("res/PawnB.png"), this));
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		}

		if (e.getSource() == difficulty) {
			difficultyLevel = ((difficultyLevel + 1) % 5);
			//System.out.println("Difficulty is: " + (difficultyLevel + 1));
		}
		if (e.getSource() == onlineHelp) {
			try {
				String url = "http://en.wikipedia.org/wiki/Chess";
				java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			} catch (java.io.IOException err) {
				System.out.println(err.getMessage());
			}

		}
		if (e.getSource() == volume) {
			volumeLevel = ((volumeLevel + 10) % 110);
			System.out.println("Volume is: " + (volumeLevel));
		}
		if (e.getSource() == sound) {
			soundOn = !soundOn;
			if (soundOn) {
				System.out.println("Sound is ON");
			}
			if (!soundOn) {
				System.out.println("Sound is OFF");
			}
		}
	}

	public static void main(String[] args) {
		ChessboardGUI chess = new ChessboardGUI();
	}

	private void setUp() {
		for (int i = 0; i < BOARDLENGTH; i++) {
			for (int j = 0; j < BOARDLENGTH; j++) {
				Position temp = new Position(i, j);
					if ((i + j) % 2 == 0) {
						squares[i][j] = new Square(this, null, temp,
								Color.WHITE);
						main.add(squares[i][j]);
					} else {
						squares[i][j] = new Square(this, null, temp,
								Color.BLACK);
						main.add(squares[i][j]);
					}
				}
			}
		}

	public boolean isOccupied(Position p) {
		/**
		 * If a square does not have an icon(icon == null) then there is no
		 * piece on the square.
		 */
		//System.out.println("Checking occupation of square [" + p.getX() + "][" + p.getY() + "]");
		try{
		return squares[p.getX()][p.getY()].getPiece() != null;
		}catch(NullPointerException e){
			return false;
		}
	}

	public Square getSquare(Position position) {
		return squares[position.getX()][position.getY()];
	}

}
