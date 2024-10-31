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
        String seedInput = "";
        char number = '0';
        boolean newGame = false;

        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key  = Character.toUpperCase(StdDraw.nextKeyTyped());
            if (key == 'L') {
                GameState loadedState = LoadGame.loadGame("Game.ser");
                if (loadedState != null) {
                    finalWorldFrame = loadedState.getMap();
                    int x = loadedState.getPlayerX();
                    int y = loadedState.getPlayerY();
                    mp = new MapGenerator(WIDTH, HEIGHT, SEED, finalWorldFrame, x, y);
                    break;
                }
            } else if (key == 'N') {
                newGame = true;
                finalWorldFrame = new TETile[WIDTH][HEIGHT];
            } else {
                if (Character.isDigit(key)) {
                    seedInput += key;
                } else {
                    break;
                }
            }
        }

        if (newGame) {
            SEED = Long.parseLong(seedInput);
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    finalWorldFrame[x][y] = Tileset.NOTHING;
                }
            }
            mp = new MapGenerator(WIDTH, HEIGHT, SEED, finalWorldFrame, 0, 0);
            mp.mapGenerate();
        }

        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(finalWorldFrame);

        StdDraw.enableDoubleBuffering();
        char nextMove;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                nextMove = Character.toUpperCase(StdDraw.nextKeyTyped());
            } else {
                /* Random char to represent not move to avoid the constant moves */
                nextMove = 'K';
            }
            if (nextMove == 'Q') {
                SaveGame sg = new SaveGame();
                sg.saveGame(new GameState(finalWorldFrame, mp.getPlayerX(), mp.getPlayerY()), "game.ser");
                System.exit(0);
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
            }
            ter.renderFrame(finalWorldFrame);
            HUD();
            StdDraw.show();
            StdDraw.pause(20);

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
        return finalWorldFrame;
    }

    public void HUD() {
        String info = "";
        int x = (int) StdDraw.mouseX();
        int y = (int) StdDraw.mouseY();
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            if (finalWorldFrame[x][y] == Tileset.WALL) {
                info = "Wall";
            } else if (finalWorldFrame[x][y] == Tileset.FLOOR) {
                info = "Floor";
            } else if (finalWorldFrame[x][y] == Tileset.NOTHING) {
                info = "Nothing here";
            } else if (finalWorldFrame[x][y] == Tileset.PLAYER) {
                info = "You";
            } else if (finalWorldFrame[x][y] == Tileset.LOCKED_DOOR) {
                info = "This is a locked door";
            }
        } else {
            info = "";
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(1, HEIGHT - 1, 5,1);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 10));
        StdDraw.textLeft(1, HEIGHT - 1, info);
}


    public static void main(String args[]) {
        Game g  = new Game();
        g.playWithKeyboard();
    }
}