package main;

public class Position {
	int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String toString() {
		return "x: " + x + " y: " + y;
	}

	public boolean inBounds() {
		if (x < 7 && y < 7 && x >= 0 && y >= 0)
			return true;
		else
			return false;
	}
}
