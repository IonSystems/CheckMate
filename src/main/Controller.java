package main;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import player.Player;

public class Controller {
	
	Player whitePlayer;
	Player blackPlayer;
	Player inPlay;
	Board board;
	//TODO: Timer timer;
	private  Controller(){
		board = new Board();
		whitePlayer = new Player(true);
		blackPlayer = new Player(false);
		inPlay = whitePlayer;

		
	}
	
	public static Controller instance;
	static {
		instance = new Controller();
	}
	public static Controller getInstance() {
		return instance;
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
	public boolean isTurn(boolean playerColour){
		return (inPlay.getColour() == playerColour);
	}
	
	
	
}
