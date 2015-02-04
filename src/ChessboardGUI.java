import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


//GUI for showing the chess AI

public class ChessboardGUI extends JFrame implements ActionListener {

	JPanel main;
	Square [][] squares;
	final int BOARDLENGTH = 9; 
	
	
	public ChessboardGUI(){
		main = new JPanel(new GridLayout(9,9));
		squares = new Square [9][9];
		setUp();
		this.setResizable(true);
		this.setSize(1000,1000);
		this.add(main);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main (String []args){
		ChessboardGUI chess = new ChessboardGUI();

	}

	private void setUp() {
		for (int i = 0; i < BOARDLENGTH-1; i++){
			int [] temp2 = {i, 0};
			squares[i][0] = new Square(null, temp2, Color.GRAY);
			main.add(squares[i][0]);
			for (int j = 1; j < BOARDLENGTH; j++){
				int [] temp = {i, j-1};
				if (i != 8){
				if ((i + j)%2 == 0){
					squares[i][j-1]= new Square(null, temp, Color.WHITE);
					main.add(squares[i][j-1]);
				}
				else {
					squares[i][j-1]= new Square(null, temp, Color.BLACK);
					main.add(squares[i][j-1]);
				}
				}
			}
		
		}
		for (int k = 0; k < BOARDLENGTH; k++){
			int [] temp3 = {8, k};
			squares[8][k] = new Square(null, temp3, Color.GRAY);
			main.add(squares[8][k]);
		}
		}	
	}
