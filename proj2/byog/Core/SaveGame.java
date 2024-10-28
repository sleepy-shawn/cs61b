package byog.Core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveGame {
	public void saveGame(GameState gameState, String fileName) {
		try(FileOutputStream fileOut = new FileOutputStream(fileName);
		    ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(gameState);
			System.out.println("Game Saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
