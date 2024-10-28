package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Set;

public class Game implements Serializable {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public long SEED;
    public TETile[][] finalWorldFrame;
    public MapGenerator mp;

    // Method used for playing a fresh game. The game should start from the main menu.
    public void playWithKeyboard() {
        GameUI ui = new GameUI(WIDTH, HEIGHT);
        ui.drawMenu();

        String input = "";
        char nextMove = 0;

        if (StdDraw.nextKeyTyped() == 'N') {
            Character number = StdDraw.nextKeyTyped();
            while (Character.isDigit(number)) {
                input += String.valueOf(number);
                if (StdDraw.hasNextKeyTyped()) {
                    number = StdDraw.nextKeyTyped();
                } else {
                    break;
                }
            }
            nextMove = number;
            SEED = Long.parseLong(input);
            finalWorldFrame = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    finalWorldFrame[x][y] = Tileset.NOTHING;
                }
            }
            mp = new MapGenerator(WIDTH, HEIGHT, SEED, finalWorldFrame);
            ter.initialize(WIDTH, HEIGHT);
            mp.mapGenerate();
            ter.renderFrame(finalWorldFrame);


        } else if (StdDraw.nextKeyTyped() == 'L') {
            GameState loadedState = LoadGame.loadGame("game.ser");
            if (loadedState != null) {
                SEED = loadedState.getSeed();
                finalWorldFrame = new TETile[WIDTH][HEIGHT];
                mp = new MapGenerator(WIDTH, HEIGHT, SEED, finalWorldFrame);
            }

        }


        while (true) {
            if (nextMove == 'Q') {
                SaveGame sg = new SaveGame();
                sg.saveGame(mp.getGameState(), "game.ser");
                break;
            } else {
                if (nextMove == 'A') {
                    mp.left();
                } else if (nextMove == 'D') {
                    mp.right();
                } else if (nextMove == 'S') {
                    mp.down();
                } else if (nextMove == 'W') {
                    mp.up();
                }
                if (!StdDraw.hasNextKeyTyped()) {
                    break;
                } else {
                    nextMove = StdDraw.nextKeyTyped();
                }
            }
        }
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
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */

    public TETile[][] playWithInputString(String input) {
        // First deal with the input of mode and seed
        int i = 0;
        char mode = input.charAt(i);
        if (mode != 'N') {
            throw new IllegalArgumentException(" No game saved!");
        }
        i += 1;
        finalWorldFrame = new TETile[Game.WIDTH][Game.HEIGHT];
        StringBuilder sb = new StringBuilder();
        while (i < input.length() && Character.isDigit(input.charAt(i))) {
            sb.append(input.charAt(i));
            i += 1;
        }
        SEED = Integer.parseInt(sb.toString());
        MapGenerator mp = new MapGenerator(Game.WIDTH, Game.HEIGHT, SEED, finalWorldFrame);

        // Read the following instructions

        while (i < input.length()) {
            char move = input.charAt(i);
            if (move == ':') {
                i += 1;
                char next = input.charAt(i);
                if (next == 'Q') {
                    // Save the decuments
                }
            } else {
                // Make the corresponding move
            }
        }
        return finalWorldFrame;
    }

    public void HUD() {
        while (true) {
            int x = (int)StdDraw.mouseX();
            int y = (int)StdDraw.mouseY();
            if (finalWorldFrame[x][y] == Tileset.WALL) {
                StdDraw.text(5, 28, "WALL");
            } else if (finalWorldFrame[x][y] == Tileset.FLOOR) {
                StdDraw.text(5, 28, "FLOOR");
            } else if (finalWorldFrame[x][y] == Tileset.NOTHING) {
                StdDraw.text(5, 28, "Nothing here");
            } else if (finalWorldFrame[x][y] == Tileset.PLAYER) {
                StdDraw.text(5, 28, "You");
            } else if (finalWorldFrame[x][y] == Tileset.LOCKED_DOOR) {
                StdDraw.text(5, 28, "Locked Door");
            }
        }
    }
}