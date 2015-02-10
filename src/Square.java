import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Square class for chess
 * 
 * @author Andrew J Rigg, Cameron A. Craig
 *
 */
public class Square extends JLabel implements MouseListener {

	Position position;
	ChessPiece piece;
	String squareName;
	char[] columns = { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	String cols = "ABCDEFG";
	boolean selected = true;
	int BOARDLENGTH = 9;
	ChessboardGUI board;

	public Square(ChessboardGUI board, ChessPiece piece, Position position,
			Color colour) {
		super("", SwingConstants.CENTER);
		this.piece = piece;
		this.board = board;
		this.position = position;
		this.setBackground(colour);
		this.setOpaque(true);
		this.setSize(new Dimension(50, 50));
		this.setVisible(true);
		if (this.piece != null) {
			this.setIcon(piece.getIcon());
		}
		this.addMouseListener(this);


		// createSquareName(position.getX(), position.getY());
		squareName = createSquareName(position.getX(), position.getY());
		//this.setText(squareName);
	}

	public String createSquareName(int column, int row) {
		if (row != 8) {
			String name = "" + columns[column + 1] + (BOARDLENGTH - row - 1);
			return name;
		} else {
			String name = "" + columns[column];
			squareName = name;
			return name;
		}

	}

	public void addPiece(ChessPiece chessPiece) {
		if (chessPiece == null) {
			this.setIcon(null);
			this.piece = null;
		} else {
			piece = chessPiece;
			this.setIcon(piece.getIcon());
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Click: x:" + position.getX() + " y:"
				+ position.getY());
		if (piece != null)
			System.out.println(piece.printValidPositions());
		board.checkMoveable(this, this.getPiece());

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public ChessPiece getPiece() {
		if (piece == null)
			return null;
		return piece;
	}

	public Position getPosition() {
		return new Position(position.getX(), position.getY());
	}

	public String toString() {
		return "" + position.getX() + position.getY();
	}

	public boolean hasPiece() {
		try {
			if (piece == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			System.err.println("NULL POINTER!");
			return false;
		}

	}

	public boolean hasImmediatePawn() {
		try{
			if (piece == null){
				return false;
			}
			else if (piece.getType() == Piece.PAWN && piece.getMoveCounter() == 1 && this == board.getLastMove()){
				return true;
				}			
		}catch(NullPointerException e){
			return false;
		}
		return selected;
	}

}
