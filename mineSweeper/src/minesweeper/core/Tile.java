package minesweeper.core;

/**
 * Tile of a field.
 */
public abstract class Tile {
    
    /** Tile states. */
    public enum State {
        /** Open tile. */
        OPEN, 
        /** Closed tile. */
        CLOSED,
        /** Marked tile. */
        MARKED
    }
    
    /** Tile state. */
    private State state = State.CLOSED; // kedze private neda sa k nej pristupovat z inej triedy cez objekt
        
    /**
     * Returns current state of this tile.
     * @return current state of this tile
     */
    public State getState() {
        return state;
    }

    /**
     * Sets current current state of this tile.
     * @param state current state of this tile
     */
    void setState(State state) {
        this.state = state;
    }

	@Override
	public String toString() {
		String mineStr = "";
		if(getState() == state.CLOSED){
			mineStr = "-";
		}
		else if(getState() == state.MARKED){
			mineStr = "M";	
		}
		return mineStr;
	}
    
}
