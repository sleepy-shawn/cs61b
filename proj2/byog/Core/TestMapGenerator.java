package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class TestMapGenerator {
	private static final int WIDTH = 80;
	private static final int HEIGHT = 30;

	private static final long SEED = 666662;

	public static void main(String[] args) {
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);
		// initialize tiles
		TETile[][] world = new TETile[WIDTH][HEIGHT];
		for (int x = 0; x < WIDTH; x += 1) {
			for (int y = 0; y < HEIGHT; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}
		MapGenerator mg = new MapGenerator(WIDTH, HEIGHT, SEED, world, 0, 0);
		mg.mapGenerate();
		ter.renderFrame(world);
	}
}
