import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Create a chess piece with it's various attributes
 */
public class ChessPiece{

	ImageIcon image;
	int x;
	int y;
	
	
	int points;
	ArrayList<Integer[]> available = new ArrayList<Integer[]>();
	public ChessPiece(){};
	
	public ChessPiece(int initX, int initY, ImageIcon image){
		this.image = image;
		this.x = initX;
		this.y = initY;
		
		
		
	}
	
	public ImageIcon getIcon(){
		return this.image;
	}
	
}
