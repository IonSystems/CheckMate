package main;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import player.Player;

public class Controller {
	
	Player whitePlayer;
	Player blackPlayer;
	Player inPlay;
	ChessboardGUI gui;
	//TODO: Timer timer;
	public Controller(){
		whitePlayer = new Player(Color.WHITE);
		blackPlayer = new Player(Color.BLACK);
		inPlay = whitePlayer;
		gui = new ChessboardGUI(this);
		
	}
	public void nextPlayer(){
		if(inPlay == whitePlayer) inPlay = blackPlayer;
		else if(inPlay == blackPlayer) inPlay = whitePlayer;
	}
	public void setPlayer(Player player){
		inPlay = player;
	}
	public static void main(String[] args) {
		Controller controller = new Controller();
		
	}
	public boolean isTurn(Player player){
		return (player == inPlay);
	}
	public boolean isTurn(Color playerColour){
		return (inPlay.getColour() == playerColour);
	}
	public ChessboardGUI getGUI(){
		return gui;
	}
	
	private static void setupForPromotion(Controller controller){
		
		final Board board = controller.getGUI().getBoard();
		Position pTo = new Position(4,3);
		Position pFrom = new Position(6,3);
		Square from = board.getSquare(pFrom);
		Square to = board.getSquare(pTo);
		Move move = from.getPiece().getMove(to.getPosition());
		boolean moveSuccess = board.movePiece(from,to,move);
		
		
		
		//Move 2 (Black Move 1)
		pTo = new Position(3,5);
		pFrom = new Position(1,5);
		to = board.getSquare(pTo);
		from = board.getSquare(pFrom);
		move = from.getPiece().getMove(to.getPosition());
		moveSuccess = board.movePiece(from,to,move);
		
		
		
		
		//Move 3 (White Move 2)
		pTo = new Position(3,3);
		pFrom = new Position(4,3);
		to = board.getSquare(pTo);
		from = board.getSquare(pFrom);
		move = from.getPiece().getMove(to.getPosition());
		moveSuccess = board.movePiece(from,to,move);
		
	}
}
