package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import pieces.Pawn;
import pieces.Piece;

public class PawnSelectPane extends JWindow implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
   private JFrame mainFrame;
   private Color colour;
   private JPanel controlPanel;
 
   
   JButton rook,king,queen,knight,bishop;
   Pawn pawn;
   public PawnSelectPane(Board board, Pawn pawn){
	   this.board = board;
	   this.pawn = pawn;
	   if(pawn.getColour()) colour = Color.white;
	   else colour = Color.black;
      prepareGUI();
   }
   public PawnSelectPane(){
	   this.board = new Board();
	  colour = Color.white;
      prepareGUI();
   }

   
public static void main(String[] args){
	   Board board = new Board();
	   //board.setupPieces();
	  // board.movePiece(board.getSquare(new Position(6, 5)),board.getSquare(new Position(4,5)), new Move(-2,0));
	   //ImageIcon pi = new ImageIcon("res/PawnW.png");
	   //Pawn pawn = new Pawn(Piece.PAWN, null, null, board);
      PawnSelectPane  p = new PawnSelectPane();  
      //PawnSelectPane.showJWindowDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Promotion Window");
      mainFrame.setSize(800,200
    		  );
      mainFrame.setResizable(false);
      mainFrame.setLayout(new GridLayout(1,5));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
    

     
      if(colour == Color.white){
    	  rook = new JButton(new ImageIcon("res/RookW.png"));
    	  king = new JButton(new ImageIcon("res/KingW.png"));
    	  queen = new JButton(new ImageIcon("res/QueenW.png"));
    	  knight = new JButton(new ImageIcon("res/KnightW.png"));
    	  bishop = new JButton(new ImageIcon("res/BishopW.png"));
      }else{
    	  rook = new JButton(new ImageIcon("res/RookB.png"));
          king = new JButton(new ImageIcon("res/KingB.png"));
          queen = new JButton(new ImageIcon("res/QueenB.png"));
          knight = new JButton(new ImageIcon("res/KnightB.png"));
          bishop = new JButton(new ImageIcon("res/BishopB.png"));
      }
      rook.addActionListener(this);
      king.addActionListener(this);
      queen.addActionListener(this);
      knight.addActionListener(this);
      bishop.addActionListener(this);

      controlPanel = new JPanel();
      //controlPanel.setLayout(new FlowLayout());

      mainFrame.add(rook);
      mainFrame.add(king);
      mainFrame.add(queen);
      mainFrame.add(knight);
      mainFrame.add(bishop);
      mainFrame.setVisible(true);  
   }
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == rook){
		dispose();
		System.out.println("Rook");
		board.promote(pawn, Piece.ROOK);
		//System.exit(0);
	}
	
}

}
   