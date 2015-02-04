import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * Square class for chess
 * @author ar339
 *
 */
public class Square extends JLabel{

	int [] coordinate = new int [2];
	ChessPiece piece = new ChessPiece(0, 0, null);
	String squareName;
	char [] columns = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	String cols = "ABCDEFG";
	
	
	public Square(ChessPiece piece, int [] coordinateXY, Color colour){
		this.piece = piece;
		this.coordinate = coordinateXY;
		this.setBackground(colour);
		this.setOpaque(true);
		this.setSize(new Dimension(50,50));
		this.setVisible(true);
		if(this.piece != NULL) this.setIcon(piece.getIcon());
		//squareName = createSquareName(coordinateXY[0], coordinateXY[1]);
	}

	public String createSquareName(int row, int column){
		String name = "" + columns[column]+ row+1;
		return name;
	}

	public void addPiece(ChessPiece chessPiece) {
		this.piece = chessPiece;
		
	}
}
