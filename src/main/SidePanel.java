package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class SidePanel extends JPanel {
	Board board;
	Square[][] squares;
	public SidePanel(Board board){
		super(new GridLayout(8,2));
		squares = new Square[2][8];
		//add(new JLabel("Hello"));
		
		//this.setLayout();
		for( int i = 0; i < 2; i++){
			for( int j = 0; j< 8; j++){
				Position temp = new Position(i,j);
				squares[i][j] = new Square(board, null, temp, Color.YELLOW);
				add(squares[i][j]);
			}
		}
		setVisible(true);
		setSize(400,1000);
		setMaximumSize(new Dimension(400,1000));
}
	}
	
