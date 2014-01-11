package fourInARow.model;

import fourInARow.excpetion.ColumnFullException;

import fourInARow.excpetion.ColumnOutOfRangeException;

public interface IModel{
	
	public GameStatus addDisc(int col, int playerNum) throws ColumnFullException , ColumnOutOfRangeException;
	
	public int [][] getBoard();
	
	public int getNumRows();
	
	public int getNumCols();
	
	public boolean isWinner(int[][] board, int col, int row, int playerNum);
	
	public int firstEmptyRow(int col) throws ColumnFullException;
	
}
