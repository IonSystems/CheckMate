package main;

import java.awt.Color;

import player.Player;

public class Controller {
	ChessboardGUI gui;
	Player whitePlayer;
	Player blackPlayer;
	Player inPlay;
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
}
