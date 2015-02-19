package player;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Player {
	boolean colour;
	String name;
	int rank;
	int age;
	ImageIcon profileImage;

	public Player(boolean colour){
		this.colour = colour;
	}

	public boolean getColour() {
		return colour;
	}

}
