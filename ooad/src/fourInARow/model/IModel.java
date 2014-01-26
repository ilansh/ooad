package fourInARow.model;



import java.util.Observable;

import fourInARow.excpetion.ColumnFullException;
import fourInARow.excpetion.ColumnOutOfRangeException;

/**
 * Interfaces implementing this must extend observable as well
 */
public interface IModel{
	
	
	
	public GameStatus addDisc(int col, int playerNum) throws ColumnFullException , ColumnOutOfRangeException;
	
	public int [][] getBoard();
	
	public int getNumRows();
	
	public int getNumCols();
	
	public boolean isWinner(int[][] board, int col, int row, int playerNum);
	
	public int firstEmptyRow(int col) throws ColumnFullException;
	
	public Observable getObservable();
	
}
