package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;

public class GameState implements Serializable {
	private static final long serialVersionUID = 1L;
	private TETile[][] finalWorldFrame;
	private int playerX;
	private int playerY;

	public GameState(TETile[][] finalWorldFrame, int x, int y) {
		this.finalWorldFrame = finalWorldFrame;
		this.playerX = x;
		this.playerY = y;

	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public TETile[][]getMap() {
		return finalWorldFrame;
	}
}
