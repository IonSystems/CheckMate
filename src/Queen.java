import javax.swing.ImageIcon;

public class Queen extends ChessPiece{
	
	ImageIcon image; 
	
	public Queen (){
		this.points = 9;
		this.image = new ImageIcon("QueenW.png");
	}
		
}
