import java.util.ArrayList;
import javax.swing.JLabel;

/**
 * Create a chess piece with it's various attributes
 */
public class ChessPiece extends JLabel{

	int x;
	int y;
	int points;
	ArrayList<Integer[]> available = new ArrayList<Integer[]>();
	public ChessPiece(){};
	
	public ChessPiece(int initX, int initY){
		this.x = initX;
		this.y = initY;	
	}
	
}
