package byog.Core;

import java.io.*;

public class LoadGame {


	public static GameState loadGame(String filename) {
		GameState gameState = null;

		try(FileInputStream fileInputStream = new FileInputStream(filename);
		    ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
			gameState = (GameState) in.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("File no found! Check if saved.");
		} catch (IOException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		return gameState;
	}
}
