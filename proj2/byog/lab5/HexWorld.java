package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
	public void addHexgonal(TETile[][] world, position p, int s, TETile t) {
		int[][] fill = ifFill(s);
		int x = p.startX;
		int y = p.startY;
		for (int i = x; i < 3 * s - 2 + x; i += 1) {
			for (int j = y; j < 2 * s + y; j += 1) {
				if (fill[i - x][j - y] == 1) {
					world[i][j] = t;
				}
			}
		}
	}

	// helper class: start position at the left corner of the hexworld
	private static class position {
		int startX;
		int startY;
		public position(int x, int y) {
			startX = x;
			startY = y;
		}
	}

	// helper methods to fill in the block with 0, 1
	// remember to change for the static.
	private static int [][] ifFill(int s) {
		int max = s * 3 - 2;// the max num of items of a row
		int [][] block = new int[max][2 * s];
		for (int i = 0; i < 2 * s ; i = i + 1) {
			int startNum = start1(i, s);
			int count = fillNum(i, s);
			for (int j = 0; j < count; j = j + 1) {
				block[startNum][i] = 1;
				startNum += 1;
			}
		}
		return block;
	}

	// another helper methods for calculating the num of 1 of every row
	// x starts from 0
	private static int fillNum(int x, int s) {
		int num;
		if (x < s) {
			num = s + 2 * x;
		} else {
			num = 3 * s - 2 - 2*(x - s);
		}
		return num;
	}

	// another helper methods about where to start 1
	// y starts from 0
	private static int start1 (int y, int s) {
		int p;
		if (y < s){
		    p = s - 1 - y;
		} else {
			p = y - s;
		}
		return p;
	}


	// @ source from BoringWorldDemo
	public static void main(String[] args) {
		// initialize the tile rendering engine
		TERenderer ter = new TERenderer();
		ter.initialize(40, 30);


		TETile[][] world = new TETile[40][30];
		for (int x = 0; x < 40; x += 1) {
			for (int y = 0; y < 30; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}


		// add Hexgonal
		position p = new position(10, 10);
		HexWorld hexWorld = new HexWorld();
		hexWorld.addHexgonal(world,p,4, Tileset.GRASS);

		// draws the world to the screen
		ter.renderFrame(world);
	}

	public static class TestMapGenerator {
	}
}
