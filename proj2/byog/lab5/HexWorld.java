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
	private int [][] ifFill(int s) {
		int max = s * 3 - 2;// the max num of items of a row
		int [][] block = new int[max][max];


	}

	// another helper methods for calculating the num of 1 of every row
	// x starts from 0
	private int fillNum(int x, int s) {
		int num;
		if (x < s) {
			num = s + 2 * x;
		} else {
			num = s * 3 - 2 - 2 * (x - s);
		}
		return num;
	}

	// another helper methods about where to start 1
	// y starts from 0
	private int start1 (int y, int s) {
		int p = s + y;
	}

}
