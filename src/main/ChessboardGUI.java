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
	private SidePanel sidePanel1;
	private SidePanel sidePanel2;

	public ChessboardGUI() {
		board = new Board();
		sidePanel1 = new SidePanel(board);
		sidePanel2 = new SidePanel(board);
		setUp();
		board.setupPieces();
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
		this.add(board.main,BorderLayout.CENTER);
		this.add(sidePanel1,BorderLayout.EAST);
		this.add(sidePanel2,BorderLayout.WEST);
		setJMenuBar(menuBar);
		this.setResizable(true);
		this.setSize(1200, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	

	

	// private boolean rulesAdheredTo(Square to, Square from) {
	// return (to.getPiece().getValidPositions().contains(from.getPosition()));
	// }

	
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
					board.squares[i][j] = new Square(board, null, temp,
							Color.WHITE);
					board.main.add(board.squares[i][j]);
				} else {
					board.squares[i][j] = new Square(board, null, temp,
							Color.BLACK);
					board.main.add(board.squares[i][j]);
				}
			}
		}
	}

	

}