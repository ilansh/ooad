package fourInARow.model;


import fourInARow.excpetion.ColumnFullException;

import fourInARow.excpetion.ColumnOutOfRangeException;

public interface IModel{
	
	public GameStatus addDisc(int col, PlayerNum playerNum) throws ColumnFullException , ColumnOutOfRangeException;
	
	public PlayerNum [][] getBoard();
	
	public int getNumRows();
	
	public int getNumCols();
	
	public boolean isWinner(PlayerNum[][] board, int col, int row, PlayerNum playerNum);
	
	public int firstEmptyRow(int col) throws ColumnFullException;
	
}
