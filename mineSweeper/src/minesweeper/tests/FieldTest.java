package minesweeper.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Mine;

public class FieldTest {
	static final int ROWS = 9;
	static final int COLUMNS = 9;
	static final int MINES = 10;
	int mineCount = 0;
	int clueCount = 0;

	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test                
    public void isSolved() {
        Field field = new Field(ROWS, COLUMNS, MINES);
        
        // Po vytvoreni hracieho pola musi byt hracie pole v stave
        // priebehu hry GameState.PLAYING
        assertEquals(GameState.PLAYING, field.getState());
        
        int open = 0;
        for(int row = 0; row < field.getRowCount(); row++) {
            for(int column = 0; column < field.getColumnCount(); column++) {
                if(field.getTiles(row, column) instanceof Clue) {
                    field.openTile(row, column);
                    open++;
                }
                //Po odkryti dlazdice typu CLUE je potrebne testovat stav uspesneho
                // ukoncenia hry.
                // Ak pocet dlazdic MINUS pocet odkrytych dlazdic = pocet min
                // potom musi byt hra v stave uspesneho ukoncenia.
                if(field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
                    // Vyraz pre test uspesneho ukoncenia hry je vyraz
                	assertEquals(GameState.SOLVED, field.getState());
                } else {
                	// Ak odkryjem dlazdicu typu CLUE je potrebne testovat
                	// aby nenastal stav neuspesneho ukoncenia hry
                	// Vyuzivame nasledovny vyraz
                    assertNotSame(GameState.FAILED, field.getState());
                }
            }
        }
        // Po odkryti vsetkych dlazdic typu CLUE musi byt hra v stave uspesneho 
        // ukoncenia hry.
        // Vyuzivame nasledovny vyraz
        assertEquals(GameState.SOLVED, field.getState());
    } 
	
	
	@Test                
    public void generate(){
        Field field = new Field(ROWS, COLUMNS, MINES);
        
        // Testujem ci sa rovna pocet zadanych riadkov/stlpcov/min s
        // poctom ktore su v poli 
        assertEquals(ROWS, field.getRowCount());
        assertEquals(COLUMNS, field.getColumnCount());
        assertEquals(MINES, field.getMineCount());
    
        //Test pre hracie pole... kazda polozka musi byt dlazdica nie NULL
        for(int i = 0; i < field.getRowCount(); i++){
        	for(int j = 0; j< field.getColumnCount(); j++){
        		assertNotNull(field.getTiles(i,j));
        		if(field.getTiles(i, j) instanceof Mine){
        			mineCount++;
        		}
        		if(field.getTiles(i,j) instanceof Clue) {
        	        clueCount++;
        	    }
        	}
        }
        assertEquals(MINES,mineCount);	
        assertEquals(ROWS * COLUMNS - mineCount, clueCount);
	}	
}



