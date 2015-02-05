import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class ChessboardGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel main;
	Square[][] squares;
	JMenuBar menuBar;
	JMenu options, help, file;
	JMenuItem save, load, exit, difficulty, sound, volume, helpPage, onlineHelp;
	final int BOARDLENGTH = 9;
	int difficultyLevel = 0;
	int volumeLevel = 50;
	boolean soundOn = true;

	public ChessboardGUI() {
		main = new JPanel(new GridLayout(9, 9));
		squares = new Square[9][9];
		setUp();
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
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==difficulty){
			difficultyLevel = ((difficultyLevel+1) % 5);
			System.out.println("Difficulty is: " + (difficultyLevel+1));
		}
		
		if (e.getSource()==exit){
			System.exit(0);
		}
		if (e.getSource()==onlineHelp){
			try {
			     String url = "http://en.wikipedia.org/wiki/Chess";
			     java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			} catch (java.io.IOException err) {
			     System.out.println(err.getMessage());
			}
			
		}
		if (e.getSource()==volume){
			volumeLevel = ((volumeLevel +10) % 110);
			System.out.println("Volume is: " + (volumeLevel));
		}
		if (e.getSource() == sound){
			soundOn = !soundOn;
			if (soundOn){
				System.out.println("Sound is ON");
			}
			if (!soundOn){
				System.out.println("Sound is OFF");
			}
		}
	}

	public int getBoardlength() {
		return BOARDLENGTH;
	}

	private void setUp() {
		for (int i = 0; i < BOARDLENGTH - 1; i++) {
			int[] temp2 = { i, 0 };
			squares[i][0] = new Square(null, temp2, Color.GRAY);
			squares[i][0].setText(squares[i][0].squareName);
			main.add(squares[i][0]);
			for (int j = 1; j < BOARDLENGTH; j++) {
				int[] temp = { i, j };
				if (i != 8) {
					if ((i + j) % 2 != 0) {
						squares[i][j - 1] = new Square(null, temp, Color.WHITE);
						main.add(squares[i][j - 1]);
					} else {
						squares[i][j - 1] = new Square(null, temp, Color.BLACK);
						main.add(squares[i][j - 1]);
					}
				}
			}

		}
		for (int k = 0; k < BOARDLENGTH; k++) {
			int[] temp3 = { 8, k };
			squares[8][k] = new Square(null, temp3, Color.GRAY);
			squares[8][k].setText(squares[8][k].squareName);
			main.add(squares[8][k]);
			
		}
	}

	public static void main(String[] args) {
		ChessboardGUI chess = new ChessboardGUI();
	}
}
