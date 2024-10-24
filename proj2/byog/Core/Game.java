package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static long SEED;
    private Random RANDOM;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        try {
            SEED = Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for seed");
        }
        RANDOM = new Random(SEED);

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        int room_Num = RANDOM.nextInt(100);
        Room[] rooms = new Room[room_Num];
        int count = 0;
        for (int i = 0; i < room_Num; i += 1) {
            Room r = new Room(finalWorldFrame);
            if (!checkOverlap(r, rooms) && !checkOutOfWorld(r)) {
                rooms[count] = r;
                count += 1;
                r.draw();
            }
        }

        connectRoom(finalWorldFrame, rooms);

        return finalWorldFrame;
    }

    // Helper class Position
    private static class Position {
        int startX;
        int startY;

        private Position(int x, int y) {
            startX = x;
            startY = y;
        }
    }

    // Fill rooms with floor
    private void fillRoom(TETile[][] world, int width, int length, Position p) {
        for (int x = p.startX; x < p.startX + width; x += 1) {
            for (int y = p.startY; y < p.startY + length; y += 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    // Draw the walls
    private void drawVertical(TETile[][] world, int length, Position p) {
        for (int i = 0; i < length; i += 1) {
            world[p.startX][p.startY - i] = Tileset.WALL;
        }
    }

    private void drawHorizontal(TETile[][] world, int length, Position p) {
        for (int i = 0; i < length; i += 1) {
            world[p.startX + i][p.startY] = Tileset.WALL;
        }
    }

    private class Room{
        TETile[][] world;
        Position p;
        int width;
        int length;

        private Room(TETile[][] wl) {
            world = wl;
            p = new Position(RANDOM.nextInt(WIDTH), RANDOM.nextInt(HEIGHT));
            width = RANDOM.nextInt(5) + 3;
            length = RANDOM.nextInt(5) + 3;
        }

        private void draw() {
            Position tempP = new Position(p.startX, p.startY);
            drawHorizontal(world, width, tempP);
            tempP = new Position(p.startX, p.startY);
            drawVertical(world, length, tempP);
            tempP = new Position(p.startX + width - 1, p.startY);
            drawVertical(world, length, tempP);
            tempP = new Position(p.startX, p.startY - length + 1);
            drawHorizontal(world, width, tempP);
            fillRoom(world, width - 2, length - 2, new Position(p.startX + 1, p.startY - 1));
        }
    }

    private boolean checkOverlap(Room r, Room[] rooms) {
        int index = 0;
        int rxMin = r.p.startX;
        int rxMax = rxMin + r.width - 1;
        int ryMin = r.p.startY;
        int ryMax = ryMin - r.length + 1;
        while (index < rooms.length && rooms[index] != null) {
            int xMin = rooms[index].p.startX;
            int xMax = xMin + rooms[index].width - 1;
            int yMin = rooms[index].p.startY;
            int yMax = yMin - rooms[index].length + 1;

            if (rxMin <= xMax && rxMax >= xMin && ryMin >= yMax && ryMax <= yMin) {
                return true;
            }
            index += 1;
        }
        return false;
    }

    private boolean checkOutOfWorld(Room r) {
        return r.p.startX < 0 || r.p.startY < 0 ||
                (r.p.startX + r.width) > WIDTH ||
                (r.p.startY - r.length + 1) < 0;
    }

    private double distanceBetweenCenters(Room r1, Room r2) {
        int x1 = r1.p.startX + r1.width / 2;
        int x2 = r2.p.startX + r2.width / 2;
        int y1 = r1.p.startY - r1.length / 2;
        int y2 = r2.p.startY - r2.length / 2;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private void connectRoom(TETile[][] world, Room[] rooms) {
        for (int i = 0; i < rooms.length && rooms[i] != null; i += 1) {
            Room currentRoom = rooms[i];
            Room closestRoom = findClosestRoom(rooms, i);
            if (closestRoom != null) {
                Position centerCurrent = new Position(
                        currentRoom.p.startX + currentRoom.width / 2,
                        currentRoom.p.startY - currentRoom.length / 2
                );
                Position centerClosest = new Position(
                        closestRoom.p.startX + closestRoom.width / 2,
                        closestRoom.p.startY - closestRoom.length / 2
                );

                if (RANDOM.nextBoolean()) {
                    drawHorizontalHallway(world, centerCurrent.startX, centerClosest.startX, centerCurrent.startY);
                    drawVerticalHallway(world, centerCurrent.startY, centerClosest.startY, centerClosest.startX);
                } else {
                    drawVerticalHallway(world, centerCurrent.startY, centerClosest.startY, centerCurrent.startX);
                    drawHorizontalHallway(world, centerCurrent.startX, centerClosest.startX, centerClosest.startY);
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

        for (int x = minX; x <= maxX; x++) {
            world[x][y] = Tileset.FLOOR;
            if (y + 1 < HEIGHT) {
                world[x][y + 1] = Tileset.WALL;
            }
            if (y - 1 >= 0) {
                world[x][y - 1] = Tileset.WALL;
            }
        }
    }

    private void drawVerticalHallway(TETile[][] world, int y1, int y2, int x) {
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        for (int y = minY; y <= maxY; y++) {
            world[x][y] = Tileset.FLOOR;
            if (x + 1 < WIDTH) {
                world[x + 1][y] = Tileset.WALL;
            }
            if (x - 1 >= 0) {
                world[x - 1][y] = Tileset.WALL;
            }
        }
    }
}
