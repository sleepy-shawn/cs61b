package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapGenerator {
	private int HEIGHT;
	private int WIDTH;
	private Room[] rooms;
	private long SEED;
	private Random RANDOM;
	private TETile[][] world;


	public MapGenerator(int w, int h, long s, TETile[][] wo) {
		WIDTH = w;
		HEIGHT = h;
		SEED = s;
		world = wo;
		RANDOM = new Random(SEED);
	}

	private class Position {
		int startX;
		int startY;

		public Position(int x, int y) {
			startX = x;
			startY = y;
		}
	}

	public void mapGenerate() {
		int room_Num = RANDOM.nextInt(300);
		rooms = new Room[room_Num];
		int count = 0;
		for (int i = 0; i < room_Num; i += 1) {
			Room r = new Room(world);
			if (!checkOverlap(r, rooms) && r.isWithinWorld(WIDTH, HEIGHT)) {
				rooms[count] = r;
				count += 1;
				r.draw();
			}
		}
	}

	private boolean checkOverlap(Room newRoom, Room[] existingRooms) {
		for (Room room : existingRooms) {
			if (room == null) {
				break;
			}
			if (newRoom.overlaps(room)) {
				return true;
			}
		}
		return false;
	}

	// Fill rooms with floor
	private void fillRoom(TETile[][] world, int width, int height, Position p) {
		for (int x = p.startX; x < p.startX + width; x += 1) {
			for (int y = p.startY; y < p.startY + height; y += 1) {
				world[x][y] = Tileset.FLOOR;
			}
		}
	}

	// Draw the walls
	private void drawVertical(TETile[][] world, int length, Position p) {
		for (int i = 0; i < length; i += 1) {
			int y = p.startY + i;
			int x = p.startX;
			if (y >= 0 && y < HEIGHT && x >= 0 && x < WIDTH) {
				world[x][y] = Tileset.WALL;
			}
		}
	}

	private void drawHorizontal(TETile[][] world, int length, Position p) {
		for (int i = 0; i < length; i += 1) {
			int x = p.startX + i;
			int y = p.startY;
			if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
				world[x][y] = Tileset.WALL;
			}
		}
	}


	private class Room {
		TETile[][] world;
		Position p;
		int width;
		int height;

		private Room(TETile[][] wl) {
			world = wl;
			width = RANDOM.nextInt(7) + 3;
			height = RANDOM.nextInt(7) + 3;

			int x = RANDOM.nextInt(WIDTH - 10) + 1;
			int y = RANDOM.nextInt(HEIGHT -10) + 1;
			p = new Position(x, y);

		}

		private void draw() {
			Position tempP = new Position(p.startX, p.startY);
			drawHorizontal(world, width, tempP);
			tempP = new Position(p.startX, p.startY);
			drawVertical(world, height, tempP);
			tempP = new Position(p.startX + width - 1, p.startY);
			drawVertical(world, height, tempP);
			tempP = new Position(p.startX, p.startY + height - 1);
			drawHorizontal(world, width, tempP);
			fillRoom(world, width - 2, height - 2, new Position(p.startX + 1, p.startY + 1));
		}

		public boolean overlaps(Room other) {
			int thisMinX = this.p.startX;
			int thisMaxX = thisMinX + this.width - 1;
			int thisMinY = this.p.startY;
			int thisMaxY = thisMinY + this.height - 1;

			int otherMinX = other.p.startX;
			int otherMaxX = otherMinX + other.width - 1;
			int otherMinY = other.p.startY;
			int otherMaxY = otherMinY + other.height - 1;

			boolean xOverlap = thisMaxX >= otherMinX && thisMinX <= otherMaxX;
			boolean yOverlap = thisMaxY >= otherMinY && thisMinY <= otherMaxY;

			return xOverlap && yOverlap;
		}

		public boolean isWithinWorld(int worldWidth, int worldHeight) {
			int minX = this.p.startX;
			int minY = this.p.startY;
			int maxX = minX + this.width - 1;
			int maxY = minY + this.height - 1;

			boolean withinXBounds = minX >= 0 && maxX < worldWidth;
			boolean withinYBounds = minY >= 0 && maxY < worldHeight;

			return withinXBounds && withinYBounds;
		}


	}
}