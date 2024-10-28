package byog.Core;

import java.io.Serializable;

public class GameState implements Serializable {
	private static final long serialVersionUID = 1L;
	private long seed;
	private int playerX;
	private int playerY;

	public GameState(long s, int x, int y) {
		seed = s;
		playerX = x;
		playerY = y;
	}

	public long getSeed() {
		return seed;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}
}
