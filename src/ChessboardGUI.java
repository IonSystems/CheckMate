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
	JMenu save, help, file, exit;
	JMenuItem open, load, difficulty;
	final int BOARDLENGTH = 9;

	public ChessboardGUI() {
		main = new JPanel(new GridLayout(9, 9));
		squares = new Square[9][9];
		setUp();
		menuBar = new JMenuBar();
		save = new JMenu("Save");
		help = new JMenu("Help");
		file = new JMenu("File");
		exit = new JMenu("Exit");
		open = new JMenuItem("Open");
		open.addActionListener(this);
		load = new JMenuItem("Load");
		load.addActionListener(this);
		difficulty = new JMenuItem("Difficulty");
		difficulty.addActionListener(this);
		file.add(open);
		file.add(load);
		file.add(difficulty);
		exit.addActionListener(this);
		menuBar.add(file);
		menuBar.add(save);
		menuBar.add(help);
		menuBar.add(exit);
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
			System.exit(0);
			System.out.println(5);
		}
		//Don't understand why this isn't working!
		if (e.getSource()==exit){
			System.out.println("EHH?!");
			System.exit(0);
		}
		
	}

	public int getBoardlength() {
		return BOARDLENGTH;
	}

	private void setUp() {
		for (int i = 0; i < BOARDLENGTH - 1; i++) {
			int[] temp2 = { i, 0 };
			squares[i][0] = new Square(null, temp2, Color.GRAY);
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
			main.add(squares[8][k]);
		}
	}

	public static void main(String[] args) {
		ChessboardGUI chess = new ChessboardGUI();

	}
}
