import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Square class for chess
 * @author ar339
 *
 */
public class Square extends JLabel{
	
	int [] coordinate = new int [2];
	ChessPiece piece = new ChessPiece(0, 0);
	String squareName;
	char [] columns = {' ','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	final int BOARDLENGTH = 9;
	
	
	public Square(ChessPiece piece, int [] coordinateXY, Color colour){
		super("",SwingConstants.CENTER);
		this.piece = piece;
		this.coordinate = coordinateXY;
		this.setBackground(colour);
		this.setOpaque(true);
		this.setSize(new Dimension(50,50));
		this.setVisible(true);
		squareName = createSquareName(coordinateXY[0], coordinateXY[1]);
		//this.setText(squareName);
	}

	public String createSquareName(int row, int column){
		if(row != 8){
		String name = "" + columns[column]+ (BOARDLENGTH -row-1);
		return name;
		}
		else {
		String name = "" + columns[column];
		return name;
		}

	}
}
