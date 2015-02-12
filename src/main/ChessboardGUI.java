package main;

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

import pieces.ChessPiece;
import pieces.Pawn;
import pieces.Piece;

//GUI for showing the chess AI

public class ChessboardGUI extends JFrame implements ActionListener {

	int difficultyLevel = 0;
	int volumeLevel = 50;
	boolean soundOn = true;
	public Board board;
	public JMenuBar menuBar;
	public JMenu options;
	public JMenu help;
	public JMenu file;
	public JMenuItem save;
	public JMenuItem load;
	public JMenuItem exit;
	public JMenuItem difficulty;
	public JMenuItem sound;
	public JMenuItem volume;
	public JMenuItem helpPage;
	public JMenuItem onlineHelp;
	//private SidePanel sidepanel1;

	public ChessboardGUI() {
		board = new Board();
		//sidepanel1 = new SidePanel();
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
		this.setLayout(new BorderLayout());
		this.add(board.main);
		setJMenuBar(menuBar);
		this.setResizable(true);
		this.setSize(1000, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	void resetSquareColors() {

		for (int i = 0; i < board.BOARDLENGTH; i++) {

			for (int j = 0; j < board.BOARDLENGTH; j++) {

				if (i != 8) {
					if ((i + j) % 2 == 0) {
						board.squares[i][j].setBackground(Color.WHITE);

					} else {
						board.squares[i][j].setBackground(Color.BLACK);
					}
				}
			}

		}
	}

	void highlightValidPositions(ChessPiece piece) {
		for (Position p : piece.getValidPositions()) {
			board.squares[p.getX()][p.getY()].setBackground(Color.GREEN);
		}

	}

	// private boolean rulesAdheredTo(Square to, Square from) {
	// return (to.getPiece().getValidPositions().contains(from.getPosition()));
	// }

	private void setupPieces() {
		board.squares[7][0].addPiece(new ChessPiece(Piece.ROOK, true,
				new Position(7, 0), new ImageIcon("res/RookW.png"), this));
		board.squares[7][7].addPiece(new ChessPiece(Piece.ROOK, true,
				new Position(7, 7), new ImageIcon("res/RookW.png"), this));
		board.squares[0][7].addPiece(new ChessPiece(Piece.ROOK, false,
				new Position(0, 7), new ImageIcon("res/RookB.png"), this));
		board.squares[0][0].addPiece(new ChessPiece(Piece.ROOK, false,
				new Position(0, 0), new ImageIcon("res/RookB.png"), this));

		board.squares[7][1].addPiece(new ChessPiece(Piece.KNIGHT, true,
				new Position(7, 1), new ImageIcon("res/KnightW.png"), this));
		board.squares[7][6].addPiece(new ChessPiece(Piece.KNIGHT, true,
				new Position(7, 6), new ImageIcon("res/KnightW.png"), this));
		board.squares[0][6].addPiece(new ChessPiece(Piece.KNIGHT, false,
				new Position(0, 6), new ImageIcon("res/KnightB.png"), this));
		board.squares[0][1].addPiece(new ChessPiece(Piece.KNIGHT, false,
				new Position(0, 1), new ImageIcon("res/KnightB.png"), this));

		board.squares[7][2].addPiece(new ChessPiece(Piece.BISHOP, true,
				new Position(7, 2), new ImageIcon("res/BishopW.png"), this));
		board.squares[7][5].addPiece(new ChessPiece(Piece.BISHOP, true,
				new Position(7, 5), new ImageIcon("res/BishopW.png"), this));
		board.squares[0][5].addPiece(new ChessPiece(Piece.BISHOP, false,
				new Position(0, 5), new ImageIcon("res/BishopB.png"), this));
		board.squares[0][2].addPiece(new ChessPiece(Piece.BISHOP, false,
				new Position(0, 2), new ImageIcon("res/BishopB.png"), this));

		board.squares[7][3].addPiece(new ChessPiece(Piece.KING, true,
				new Position(7, 3), new ImageIcon("res/KingW.png"), this));
		board.squares[7][4].addPiece(new ChessPiece(Piece.QUEEN, true,
				new Position(7, 4), new ImageIcon("res/QueenW.png"), this));
		board.squares[0][3].addPiece(new ChessPiece(Piece.KING, false,
				new Position(0, 3), new ImageIcon("res/KingB.png"), this));
		board.squares[0][4].addPiece(new ChessPiece(Piece.QUEEN, false,
				new Position(0, 4), new ImageIcon("res/QueenB.png"), this));
		for (int i = 0; i <= 7; i++) {
			board.squares[6][i].addPiece(new Pawn(Piece.PAWN, true,
					new Position(6, i), new ImageIcon("res/PawnW.png"), this));
			board.squares[1][i].addPiece(new Pawn(Piece.PAWN, false,
					new Position(1, i), new ImageIcon("res/PawnB.png"), this));
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		}

		if (e.getSource() == difficulty) {
			difficultyLevel = ((difficultyLevel + 1) % 5);
			// System.out.println("Difficulty is: " + (difficultyLevel + 1));
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
		for (int i = 0; i < board.BOARDLENGTH; i++) {
			for (int j = 0; j < board.BOARDLENGTH; j++) {
				Position temp = new Position(i, j);
				if ((i + j) % 2 == 0) {
					board.squares[i][j] = new Square(this, null, temp,
							Color.WHITE);
					board.main.add(board.squares[i][j]);
				} else {
					board.squares[i][j] = new Square(this, null, temp,
							Color.BLACK);
					board.main.add(board.squares[i][j]);
				}
			}
		}
	}

	public void incrementTotalMoves() {
		board.totalMoves++;
	}

}