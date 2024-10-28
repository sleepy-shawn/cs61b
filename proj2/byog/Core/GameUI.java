package byog.Core;

import byog.lab6.MemoryGame;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class GameUI {
	private int width;
	private int height;

	public GameUI(int width, int height) {
		/* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
		 * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
		 */
		this.width = width;
		this.height = height;
		StdDraw.setCanvasSize(this.width * 16, this.height * 16);
		Font font = new Font("Monaco", Font.BOLD, 30);
		StdDraw.setFont(font);
		StdDraw.setXscale(0, this.width);
		StdDraw.setYscale(0, this.height);
		StdDraw.clear(Color.BLACK);
		StdDraw.enableDoubleBuffering();
	}

	public void drawMenu() {
		StdDraw.clear(StdDraw.BLACK);
		Font font = new Font("Arial", Font.BOLD, 40);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(40.00, 25.00, "CS61B: THE GAME!");
		Font smallFont = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(smallFont);
		StdDraw.text(40.00, 20.00, "New Game (N)");
		StdDraw.text(40.00, 15.00, "Load Game (L)");
		StdDraw.text(40.00, 10.00, "Quit (Q)");
		StdDraw.show();
	}

	public static void main(String[] args) {
		GameUI ui = new GameUI(80,30);
		ui.drawMenu();
	}
}
