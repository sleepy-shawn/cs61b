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
}
     // Method used for playing a fresh game. The game should start from the main menu.
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
        TETile[][] finalWorldFrame = new TETile[Game.WIDTH][Game.HEIGHT];
        StringBuilder sb = new StringBuilder();
        while (i < input.length() && Character.isDigit(input.charAt(i))) {
            sb.append(input.charAt(i));
            i += 1;
        }
        int seed = Integer.parseInt(sb.toString());
        MapGenerator mp = new MapGenerator(Game.WIDTH, Game.HEIGHT, seed, finalWorldFrame);

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

    }