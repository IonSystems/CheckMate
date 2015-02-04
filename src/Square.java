import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * Square class for chess
 * @author Andrew J Rigg, Cameron A. Craig
 *
 */
public class Square extends JLabel implements MouseListener{

	int [] coordinate = new int [2];
	ChessPiece piece = new ChessPiece(0, 0, null);
	String squareName;
	char [] columns = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	String cols = "ABCDEFG";
	boolean selected = true;
	
	
	public Square(ChessPiece piece, int [] coordinateXY, Color colour){
		this.piece = piece;
		this.coordinate = coordinateXY;
		this.setBackground(colour);
		this.setOpaque(true);
		this.setSize(new Dimension(50,50));
		this.setVisible(true);
		if(this.piece != null){
			this.setIcon(piece.getIcon());
		}
		this.addMouseListener(this);
		//squareName = createSquareName(coordinateXY[0], coordinateXY[1]);
	}

	public String createSquareName(int row, int column){
		String name = "" + columns[column]+ row+1;
		return name;
	}

	public void addPiece(ChessPiece chessPiece) {
		if(chessPiece == null){
			this.setIcon(null);
		}
		piece = chessPiece;
		this.setIcon(piece.getIcon());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Click");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public ChessPiece getPiece() {
		return piece;
	}
}
