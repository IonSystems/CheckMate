package main;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class SidePanel extends JPanel{
	Board board;
	Square[][] squares;

	public SidePanel(Board board){
		new JPanel(new GridBagLayout());
		setForeground(Color.ORANGE);
		squares = new Square[2][8];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j< 8; j++){
				Position temp = new Position(i,j);
				squares[i][j] = new Square(board, null, temp, Color.YELLOW);
				//squares[i][j].setText("Hi");
				add(squares[i][j]);
			}
		}
	}
}
	
