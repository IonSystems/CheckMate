import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
		setupPieces();
		this.setResizable(true);
		this.setSize(1000,1000);
		this.add(main);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setupPieces() {
		squares[7][0].addPiece(new ChessPiece(7,0,new ImageIcon("res/RookW.png")));
		squares[7][7].addPiece(new ChessPiece(7,7,new ImageIcon("res/RookW.png")));
		squares[0][7].addPiece(new ChessPiece(7,0,new ImageIcon("res/RookB.png")));
		squares[0][0].addPiece(new ChessPiece(7,7,new ImageIcon("res/RookB.png")));
		
		squares[7][1].addPiece(new ChessPiece(7,0,new ImageIcon("res/KnightW.png")));
		squares[7][6].addPiece(new ChessPiece(7,7,new ImageIcon("res/KnightW.png")));
		squares[0][6].addPiece(new ChessPiece(0,7,new ImageIcon("res/KnightB.png")));
		squares[0][1].addPiece(new ChessPiece(0,0,new ImageIcon("res/KnightB.png")));
		
		squares[7][2].addPiece(new ChessPiece(7,0,new ImageIcon("res/BishopW.png")));
		squares[7][5].addPiece(new ChessPiece(7,7,new ImageIcon("res/BishopW.png")));
		squares[0][5].addPiece(new ChessPiece(0,7,new ImageIcon("res/BishopB.png")));
		squares[0][2].addPiece(new ChessPiece(0,0,new ImageIcon("res/BishopB.png")));
		
		squares[7][3].addPiece(new ChessPiece(7,0,new ImageIcon("res/KingW.png")));
		squares[7][4].addPiece(new ChessPiece(7,7,new ImageIcon("res/QueenW.png")));
		squares[0][3].addPiece(new ChessPiece(0,7,new ImageIcon("res/KingB.png")));
		squares[0][4].addPiece(new ChessPiece(0,0,new ImageIcon("res/QueenB.png")));
		for(int i = 0 ; i <=7;i++){
			squares[6][i].addPiece(new ChessPiece(6,i,new ImageIcon("res/PawnW.png")));
			squares[1][i].addPiece(new ChessPiece(1,i,new ImageIcon("res/PawnB.png")));
		}
	
		
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
