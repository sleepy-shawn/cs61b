package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        rand = new Random(seed);
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();


    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i += 1) {
            int index = rand.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[index]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(StdDraw.BLACK);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.text((double) width /2, (double) height / 2, s);
        if (!gameOver) {
            Font tips = new Font("Arial", Font.BOLD, 22);
            StdDraw.setFont(tips);
            StdDraw.text(5.00, 38.00, "Round:" + round);
            String turn;
            if (playerTurn){
                 turn = "Watch!";
            } else {
                 turn = "Play!";
            }
            StdDraw.text(20.00, 38.00,  turn);
            StdDraw.text(30.00, 38.00, ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)]);
            StdDraw.line(0, 35, 40, 35);
        }
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i += 1) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        String input = "";
        drawFrame(input);
        while (input.length() < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            } else {
                char character = StdDraw.nextKeyTyped();
                input += String.valueOf(character);
                drawFrame(input);
            }
        }
        StdDraw.pause(500);
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        gameOver = false;
        playerTurn = false;
        //TODO: Establish Game loop
        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round:" + round + "  Good Luck");
            StdDraw.pause(1500);


            String roundString = generateRandomString(round);
            flashSequence(roundString);

            playerTurn = true;
            String userInput = solicitNCharsInput(round);
            if (userInput.equals(roundString)) {
                drawFrame("Correct! Well done!");
                round += 1;
                StdDraw.pause(1500);
            } else {
                gameOver = true;
                drawFrame("Game Over! You made it to round:" + round);
            }
        }
    }

}
