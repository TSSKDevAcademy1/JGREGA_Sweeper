package minesweeper.core;

/**
 * Mine tile.
 */
public class Mine extends Tile {

	@Override
	public String toString() {		
		if(getState() == Tile.State.OPEN){		
			return  "X";			
		}
		else{
			return super.toString();
		}
	}
	
}
