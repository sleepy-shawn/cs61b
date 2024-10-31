package byog.Core;

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
	private Player player;


	public MapGenerator(int w, int h, long s, TETile[][] wo, int px, int py) {
		WIDTH = w;
		HEIGHT = h;
		SEED = s;
		world = wo;
		RANDOM = new Random(SEED);
		player = new Player(px, py);;
	}

	private class Position {
		int startX;
		int startY;

		public Position(int x, int y) {
			startX = x;
			startY = y;
		}
	}

	private class Player {
		int PlayerX;
		int PlayerY;

		public Player(int x, int y) {
			PlayerX = x;
			PlayerY = y;
		}
	}

	public int getPlayerX() {
		return player.PlayerX;
	}

	public int getPlayerY() {
		return player.PlayerY;
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
		connectRoom(world, rooms);
		addDoor(world);
		randomPlayer(world);

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

	private void connectRoom(TETile[][] world, Room[] rooms) {
		for (int i = 0; i < rooms.length && rooms[i] != null; i += 1) {
			Room currentRoom = rooms[i];
			Room closestRoom = findClosestRoom(rooms, i);
			if (closestRoom != null) {
				Position centerCurrent = new Position(
						currentRoom.p.startX + currentRoom.width / 2,
						currentRoom.p.startY + currentRoom.height / 2
				);
				Position centerClosest = new Position(
						closestRoom.p.startX + closestRoom.width / 2,
						closestRoom.p.startY + closestRoom.height / 2
				);

				if (RANDOM.nextBoolean()) {
					drawHorizontalHallway(world, centerCurrent.startX, centerClosest.startX, centerCurrent.startY);
					drawVerticalHallway(world, centerCurrent.startY, centerClosest.startY, centerClosest.startX);
				} else {
					drawVerticalHallway(world, centerCurrent.startY, centerClosest.startY, centerCurrent.startX);
					drawHorizontalHallway(world, centerCurrent.startX , centerClosest.startX, centerClosest.startY);
				}
			}
		}
	}

	private Room findClosestRoom(Room[] rooms, int index) {
		Room currentRoom = rooms[index];
		Room closestRoom = null;
		double closestDistance = Double.MAX_VALUE;
		for (int i = 0; i < index; i += 1) {
			Room otherRoom = rooms[i];
			double distance = distanceBetweenCenters(currentRoom, otherRoom);
			if (distance < closestDistance) {
				closestDistance = distance;
				closestRoom = otherRoom;
			}
		}
		return closestRoom;
	}

	private void drawHorizontalHallway(TETile[][] world, int x1, int x2, int y) {
		int minX = Math.min(x1, x2);
		int maxX = Math.max(x1, x2);

		for (int x = minX; x <= maxX; x += 1) {
			world[x][y] = Tileset.FLOOR;

			if (y + 1 < HEIGHT && world[x][y + 1] == Tileset.NOTHING) {
				world[x][y + 1] = Tileset.WALL;
			}
			if (y - 1 >= 0 && world[x][y - 1] == Tileset.NOTHING) {
				world[x][y - 1] = Tileset.WALL;
			}
		}
	}

	private void drawVerticalHallway(TETile[][] world, int y1, int y2, int x) {
		int minY = Math.min(y1, y2);
		int maxY = Math.max(y1, y2);

		for (int y = minY; y <= maxY; y += 1) {
			world[x][y] = Tileset.FLOOR;

			if (x + 1 < WIDTH && world[x + 1][y] == Tileset.NOTHING) {
				world[x + 1][y] = Tileset.WALL;
			}
			if (x - 1 >= 0 && world[x - 1][y] == Tileset.NOTHING) {
				world[x - 1][y] = Tileset.WALL;
			}

		}
	}

	private double distanceBetweenCenters(Room r1, Room r2) {
		int x1 = r1.p.startX + r1.width / 2;
		int x2 = r2.p.startX + r2.width / 2;
		int y1 = r1.p.startY - r1.height / 2;
		int y2 = r2.p.startY - r2.height / 2;
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	private void addDoor(TETile[][] world) {
		while (true) {
			int x = RANDOM.nextInt(WIDTH);
			int y = RANDOM.nextInt(HEIGHT);
			if (world[x][y] == Tileset.WALL) {
				world[x][y] = Tileset.LOCKED_DOOR;
				break;
			}
		}
	}

	private void randomPlayer(TETile[][] world) {
		while (true) {
			int x = RANDOM.nextInt(WIDTH);
			int y = RANDOM.nextInt(HEIGHT);
			if (world[x][y] == Tileset.FLOOR) {
				world[x][y] = Tileset.PLAYER;
				player.PlayerX = x;
				player.PlayerY = y;
				break;
			}
		}
	}

	public void left() {
		int x = player.PlayerX;
		int y = player.PlayerY;
		if (!world[x - 1][y].equals(Tileset.WALL)) {
			world[x][y] = Tileset.FLOOR;
			world[x - 1][y] = Tileset.PLAYER;
			player.PlayerX -= 1;
		}
	}

	public void right() {
		int x = player.PlayerX;
		int y = player.PlayerY;
		if (!world[x + 1][y].equals( Tileset.WALL)) {
			world[x][y] = Tileset.FLOOR;
			world[x + 1][y] = Tileset.PLAYER;
			player.PlayerX += 1;
		}
	}

	public void up() {
		int x = player.PlayerX;
		int y = player.PlayerY;
		if (!world[x][y + 1].equals(Tileset.WALL)) {
			world[x][y] = Tileset.FLOOR;
			world[x][y + 1] = Tileset.PLAYER;
			player.PlayerY += 1;
		}

	}

	public void down() {
		int x = player.PlayerX;
		int y = player.PlayerY;
		if (!world[x][y - 1].equals(Tileset.WALL)) {
			world[x][y] = Tileset.FLOOR;
			world[x][y - 1] = Tileset.PLAYER;
			player.PlayerY -= 1;
		}

	}
}