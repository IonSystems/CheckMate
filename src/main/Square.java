package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import pieces.ChessPiece;

/**
 * Square class for chess
 * 
 * @author Andrew J Rigg, Cameron A. Craig
 *
 */
public class Square {

	Position position;
	ChessPiece piece;
	String squareName;
	char[] columns = { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	String cols = "ABCDEFG";
	boolean selected = true;
	boolean isWhite;
	int BOARDLENGTH = 9;

	public Square(ChessPiece piece, Position position, boolean isWhite) {

		this.piece = piece;

		this.position = position;
		this.isWhite = isWhite;

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

	public boolean isOccupied() {
		/**
		 * If a square does not have an icon(icon == null) then there is no
		 * piece on the square.
		 */
		// System.out.println("Checking occupation of square [" + p.getX() +
		// "][" + p.getY() + "]");
		try {
			return piece != null;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public void setBackground(boolean isWhite) {
		this.isWhite = isWhite;

	}

	public void addPiece(ChessPiece chessPiece) {
		this.piece = chessPiece;
		
	}
}
