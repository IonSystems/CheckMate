	/**
	 * @author Cameron Craig and Andrew Rigg
	 * GUI for showing the layout of the chessboard to 
	 * go with the physical board we are creating.  This application is 
	 * to utilise AI and should be used to play against people at a distance 
	 * or for saving games.
	 */
import java.awt.BorderLayout;
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

public class ChessboardGUI extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	JPanel main;
	Square[][] squares;
	ArrayList<ChessPiece> whiteTaken;
	ArrayList<ChessPiece> blackTaken;
	JMenuBar menuBar;
	JMenu options, help, file;
	JMenuItem save, load, exit, difficulty, sound, volume, helpPage,onlineHelp;
	final int BOARDLENGTH = 10;
	Square[] selected;
	int difficultyLevel = 0;
	int volumeLevel = 50;
	boolean soundOn = true;
	int totalMoves = 0;
	Square lastMove;

	public ChessboardGUI() {
		whiteTaken = new ArrayList<ChessPiece>();
		blackTaken = new ArrayList<ChessPiece>();
		main = new JPanel(new GridLayout(10, 10));
		squares = new Square[8][8];
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
		totalMoves = 0;
		this.add(main);
		lastMove = null;
		setJMenuBar(menuBar);
		this.setResizable(true);
		this.setSize(800, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Background colour before we select a piece 
	//for use when highlighting a square.
	Color firstSelected = null;

	protected int checkMoveable(Square square, ChessPiece chessPiece) {
		/*
		 * square is the square that has been clicked. selected[0] is the
		 * starting position of the piece. selected[1] is the finishing position
		 * of the piece.
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
			movePiece(selected[0], selected[1],
					selected[0].getPiece().getMove(selected[1].getPosition()));
			selected[0] = null;
			selected[1] = null;
			return 1;
		} else if (selected[0] != null && selected[1] == null) {
			if (true/* && rulesAdheredTo(selected[0], square) */) {
				selected[1] = square;
				// Move move = selected[0].
				movePiece(selected[0], selected[1], selected[0].getPiece()
						.getMove(selected[1].getPosition()));
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
		// ChessPiece pieceBeingMoved = from.getPiece();
		boolean valid = true;
		if (from.getPiece() == null)
			return false;
		if (from.getPiece().getType() == null)
			return false;
		if (move == null)
			return false;
		if (to.hasPiece() && from.hasPiece() && move.isTakeMove()) { 
			return takePiece(from, to, move);
		}
		if (to.getBackground() != Color.green)
			valid = false;
		/*
		 * if (from.getIcon() == null || to.getIcon() != null) { return false; }
		 * else
		 */if (valid) {// Possible move
			to.addPiece(from.getPiece());
			to.getPiece().setPosition(to.getPosition());
			to.getPiece().removeFirstMove();
			to.getPiece().incrementMoves();
			to.getPiece().findValidPositions();
			from.addPiece(null);
			lastMove = to;
			return true;
			}
		 
		return false;
	}

	private boolean takePiece(Square from, Square to, Move move) {

		boolean valid = true;
		// System.out.println("Taking:" + from.getPiece().getColour() +
		// ", Taken:" + to.getPiece().getColour());
		if (from.getPiece().getColour() == to.getPiece().getColour())
			valid = false;// from.getPiece().getColour() ==
							// to.getPiece().getColour()
		if (valid) {

			boolean takenColour = to.getPiece().getColour();
			if (takenColour == true) {
				whiteTaken.add(to.getPiece());
				// Moving taken piece to the edge of the board
				// squares[7][whiteTaken.size()].addPiece(to.getPiece());
			}
			if (takenColour == false) {
				blackTaken.add(to.getPiece());
				// Moving taken piece to the edge of the board
				// squares[7][blackTaken.size()].addPiece(to.getPiece());
			}
			to.getPiece().setPlayable(false);
			to.addPiece(from.getPiece());// Move the piece to the new empty
											// square.
			to.getPiece().setPosition(to.getPosition());// Update position of
														// piece to the position
														// of the new square
			to.getPiece().findValidPositions();
			from.addPiece(null);
			lastMove = to;
			// System.out.println("Piece Taken!");
		}
		// System.out.println("Piece to be taken!");
		return false;
		// TODO Auto-generated method stub

	}

	// private boolean rulesAdheredTo(Square to, Square from) {
	// return (to.getPiece().getValidPositions().contains(from.getPosition()));
	// }

	private void setupPieces() {
		squares[0][7].addPiece(new ChessPiece(Piece.ROOK, true, new Position(0,	7), new ImageIcon("res/RookW.png"), this));
		squares[7][7].addPiece(new ChessPiece(Piece.ROOK, true, new Position(7, 7), new ImageIcon("res/RookW.png"), this));
		squares[7][0].addPiece(new ChessPiece(Piece.ROOK, false, new Position(7, 0), new ImageIcon("res/RookB.png"), this));
		squares[0][0].addPiece(new ChessPiece(Piece.ROOK, false, new Position(0, 0), new ImageIcon("res/RookB.png"), this));

		squares[1][7].addPiece(new ChessPiece(Piece.KNIGHT, true, new Position(1, 7), new ImageIcon("res/KnightW.png"), this));
		squares[6][7].addPiece(new ChessPiece(Piece.KNIGHT, true, new Position(6, 7), new ImageIcon("res/KnightW.png"), this));
		squares[6][0].addPiece(new ChessPiece(Piece.KNIGHT, false,	new Position(6, 0), new ImageIcon("res/KnightB.png"), this));
		squares[1][0].addPiece(new ChessPiece(Piece.KNIGHT, false,	new Position(1, 0), new ImageIcon("res/KnightB.png"), this));

		squares[2][7].addPiece(new ChessPiece(Piece.BISHOP, true, new Position(	2, 7), new ImageIcon("res/BishopW.png"), this));
		squares[5][7].addPiece(new ChessPiece(Piece.BISHOP, true, new Position(		5, 7), new ImageIcon("res/BishopW.png"), this));
		squares[5][0].addPiece(new ChessPiece(Piece.BISHOP, false,	new Position(5, 0), new ImageIcon("res/BishopB.png"), this));
		squares[2][0].addPiece(new ChessPiece(Piece.BISHOP, false,	new Position(2, 0), new ImageIcon("res/BishopB.png"), this));

		squares[3][7].addPiece(new ChessPiece(Piece.KING, true, new Position(3,7), new ImageIcon("res/KingW.png"), this));
		squares[4][7].addPiece(new ChessPiece(Piece.QUEEN, true, new Position(4, 7), new ImageIcon("res/QueenW.png"), this));
		squares[3][0].addPiece(new ChessPiece(Piece.KING, false, new Position(	3, 0), new ImageIcon("res/KingB.png"), this));
		squares[4][0].addPiece(new ChessPiece(Piece.QUEEN, false, new Position(4, 0), new ImageIcon("res/QueenB.png"), this));
		for (int i = 0; i <= 7; i++) {
			squares[i][6].addPiece(new ChessPiece(Piece.PAWN, true, new Position(i, 6), new ImageIcon("res/PawnW.png"), this));
			squares[i][1].addPiece(new ChessPiece(Piece.PAWN, false, new Position(i, 1), new ImageIcon("res/PawnB.png"), this));
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		}

		if (e.getSource() == difficulty) {
			difficultyLevel = ((difficultyLevel + 1) % 5);
			System.out.println("Difficulty is: " + (difficultyLevel + 1));
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
		@SuppressWarnings("unused")
		ChessboardGUI chess = new ChessboardGUI();
	}

	private void setUp() {
		for (int i = 0; i < BOARDLENGTH; i++) {
			for (int j = 0; j < BOARDLENGTH; j++) {

				Position temp = new Position(j, i);
				if (i == 0 || i == 9|| j == 0 || j == 9){
					main.add(new Square(this, null, temp, Color.GRAY));
				}
				else if ((i + j) % 2 == 0) {
					squares[j-1][i-1] = new Square(this, null, temp, Color.WHITE);
					main.add(squares[j-1][i-1]);
				} else {
					squares[j-1][i-1] = new Square(this, null, temp, Color.BLACK);
					main.add(squares[j-1][i-1]);
				}
			}
		}
	}

	public boolean isOccupied(Position p) {
		/**
		 * If a square does not have an icon(icon == null) then there is no
		 * piece on the square.
		 */
		try {
			return squares[p.getX()][p.getY()].getPiece() != null;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public Square getSquare(Position position) {
		return squares[position.getX()][position.getY()];
	}
	
	public Square getLastMove(){
		return lastMove;
	}
	
	public void incrementTotalMoves() {
		totalMoves++;
	}

}