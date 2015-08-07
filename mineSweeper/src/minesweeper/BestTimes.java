package minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import minesweeper.BestTimes.PlayerTime;

import java.lang.*;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
	/** List of best player times. */
	private static List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	public Iterator<PlayerTime> iterator() {
		return playerTimes.iterator();
	}

	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name ot the player
	 * @param time
	 *            player time in seconds
	 */
	// funkcia pre pridanie hraca do ArrayList
	public static void addPlayerTime(String name, int time) {

		playerTimes.add(new PlayerTime(name, time));
		Collections.sort(playerTimes); // usporiadanie zoznamu
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		// throw new UnsupportedOperationException("Method toString not yet
		// implemented");
		StringBuilder str = new StringBuilder();
		Formatter formatter = new Formatter(str);

		Iterator<PlayerTime> ItTime = playerTimes.iterator();
		while (ItTime.hasNext()) {
			formatter.format("%s %n", ItTime.next().toString());
		}
		return formatter.toString();
		// for(int i = 0; playerTimes.get(i) != null; i++)
		// System.out.println(playerTimes.get(i).toString());
		// return playerTimes.get(0).toString();
	}

	/**
	 * Player time.
	 */
	public static class PlayerTime implements Comparable<PlayerTime> {
		/** Player name. */
		private final String name;;
		/** Playing time in seconds. */
		private final int time;

		/**
		 * Constructor.
		 * 
		 * @param name
		 *            player name
		 * @param time
		 *            playing game time in seconds
		 */
		public PlayerTime(String name, int time) {
			this.name = name;
			this.time = time;
		}

		public int compareTo(PlayerTime o) { // vytvorim aby som potom mohol
												// vyuzivat collection.sort()
			if (this.time > o.getTime()) {
				return 1;
			} else if (this.time == o.getTime()) {
				return 0;
			} else {
				return -1;
			}

		}

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}
	}
}
