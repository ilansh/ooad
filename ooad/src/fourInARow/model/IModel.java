package fourInARow.model;



import java.util.Observable;

import fourInARow.excpetion.ColumnFullException;
import fourInARow.excpetion.ColumnOutOfRangeException;

/**
 * Interfaces implementing this must extend observable as well
 */
public interface IModel{
	
	
	/**
	 * Add a disc to the board in the model.
	 * @param col - The column to put the disc in
	 * @param playerNum - Number of player who adds the disc
	 * @return The status of the game after the disc has been added.
	 * @throws ColumnFullException
	 * @throws ColumnOutOfRangeException
	 */
	public GameStatus addDisc(int col, int playerNum) throws ColumnFullException , ColumnOutOfRangeException;
	
	/**
	 * Get a copy of the board in this model.
	 * @return 2D int array of the board. 0 = empty spot,  1 = player1 disc, 2 = player2 disc 
	 */
	public int [][] getBoard();
	
	public int getNumRows();
	
	public int getNumCols();
	
	/**
	 * check if player playerNum can win by placing a disc at location (row, col), on the given board.
	 * @param board - the board to check on (should be similar to board format of getBoard).
	 * @param col - column to place the disc
	 * @param row - row to place the disc
	 * @param playerNum - the number of player to check winner 
	 * @return
	 */
	public boolean isWinner(int[][] board, int col, int row, int playerNum);
	
	/**
	 * Get the first empty row in the given column.
	 * @param col - column number
	 * @return the first empty row in the given column.
	 * @throws ColumnFullException
	 */
	public int firstEmptyRow(int col) throws ColumnFullException;
	
	/**
	 * Get the model's delegated Observable
	 * @return A DelegatedObservable Object
	 */
	public Observable getObservable();
	
}
