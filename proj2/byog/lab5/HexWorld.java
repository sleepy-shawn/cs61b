package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
	public void addHexgonal(TETile[][] world, position p, int s, TETile t) {

	}

	// helper class: start position at the left corner of the hexworld
	private class position {
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
				startNum = startNum + 1;
			}
		}
		return block;
	}

	// another helper methods for calculating the num of 1 of every row
	// x starts from 0
	public static int fillNum(int x, int s) {
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
	public static int start1 (int y, int s) {
		int p;
		if (y < s){
		    p = s - 1 - y;
		} else {
			p = y - s;
		}
		return p;
	}
}
