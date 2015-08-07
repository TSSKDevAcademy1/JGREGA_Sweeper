package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;
import minesweeper.core.GameState;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;
	private static Minesweeper instance;
	private long startMillis = System.currentTimeMillis();
	
	private BestTimes bestTimes = new  BestTimes();

	/**
	 * Constructor.
	 */
	public Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();

		Field field = new Field(10, 10, 10);

		userInterface.newGameStarted(field);

	}

	public int getPlayingSeconds() { // vypise cas predsavujuci dlzku

		int totalMillTime = (int) startMillis - (int) System.currentTimeMillis();
		int totalTime = Math.abs(totalMillTime / 1000);

		return totalTime;
	}

	// uprava aby trieda zodpovedala navrhovemu vzoru "{Singleton}"
	public static Minesweeper getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	
	public BestTimes getBestTimes(){
		BestTimes.addPlayerTime("john", 10);
		return bestTimes;
	}
	
	
	public static void main(String[] args) {
		new Minesweeper();
	}

}
