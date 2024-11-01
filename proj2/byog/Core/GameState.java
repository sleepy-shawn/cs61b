package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;

public class GameState implements Serializable {
	private static final long serialVersionUID = 1L;
	private TETile[][] finalWorldFrame;
	private int playerX;
	private int playerY;
	private long SEED;

	public GameState(TETile[][] finalWorldFrame, int x, int y, long SEED) {
		this.finalWorldFrame = finalWorldFrame;
		this.playerX = x;
		this.playerY = y;
		this.SEED = SEED;

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

	public long getSeed() {
		return this.SEED;
	}
}
