package minesweeper.consoleui;

import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.Minesweeper;
import minesweeper.UserInterface;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/*
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.
	 * Field)
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();

			if (field.getState() == GameState.SOLVED) { // Ak je SOLVED vypis
														// ze// uzivatel vyhral
				int totalTime = Minesweeper.getInstance().getPlayingSeconds();
				System.out.println("Congrat!! You are WINNER!! Your finally time is:" + totalTime + "seconds");
				System.exit(0);
			} else if (field.getState() == GameState.FAILED) { // Ak trafil minu
																// // = FALSE
																// tak// vypis
																// ze//
																// uzivatel//
																// prehral
				int totalTime = Minesweeper.getInstance().getPlayingSeconds();
				System.out.println("GAME OVER!! You are LOOSE!! Your playing time is:" + totalTime + "seconds");
				System.exit(0);
			}
			processInput();
		} while (true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {
		System.out.printf(field.toString());
		System.out.println("Pocet zostavajucich min:" + field.getRemainingMineCount());
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */

	// funkcia zabezpecujuca interakciu s hracom a WrongFormatException
	private void handleInput(String line) throws WrongFormatException {
		Pattern pattern = Pattern.compile("(O|M)([A-Z])([0-9]+)");
		Matcher matcher = pattern.matcher(line); // sluzni vlastne iba na to ci
													// bol spraveny MATCH{zhoda}
													// so vzorom a zadanym
													// stringom

		int exp = field.getColumnCount();

		if (matcher.matches()) { // AK JE ZHODA && (field.getColumnCount() >=
									// col))

			String d = matcher.group(3);
			int dint = Integer.parseInt(d);
			if (dint < exp) {

				char c = matcher.group(2).charAt(0); // prevediem na char
				int row = (int) c - 65; // a potom na numericku hodnotu aby som
										// zistil riadok

				int column = Integer.parseInt(matcher.group(3));

				switch (matcher.group(1).toString()) {
				case "M":
					field.markTile(row, column);
					break;
				case "O":
					field.openTile(row, column);
					break;
				}
			} else {
				throw new WrongFormatException("You ENTER bad column value!! Repeat request :)");
			}
		} else {
			throw new WrongFormatException("You ENTER bad string!! Repeat request :)");
		}
	}

	private void processInput() {

		// V eclipse sa neda samotny readLine() preto bufferedReadera
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("(M-marked : O-open , + , Row-[A-Z] , Column-[0-N]) \n Enter the input [X-exit]:");
		String line = "";

		try {
			line = bufferedReader.readLine().toUpperCase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (line.equals("X")) { // Ak 'X' ukoncim hru
			System.exit(0);
			// Minesweeper time = new Minesweeper();

		}

		try {
			handleInput(line);
		} catch (WrongFormatException ex) { // Exception
			System.out.println(ex.getMessage()); // ak nastala chyba vypise
													// Exception Message
		}
	}

}
