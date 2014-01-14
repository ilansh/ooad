package FourInARow.board;


import java.util.Arrays;

import FourInARow.board.BoardCell;


public class Board implements BoardInterface{

	private int _colSize;
	private int _rowSize;
	private int _count;
	
	//use array only for logic purpose
	//the actual representation is within the view
	private int[][] _gameBoard;
	
	public Board(int colSize, int rowSize){
		this._colSize = colSize;
		this._rowSize = rowSize;
		
		this._gameBoard = new int[colSize][rowSize];
	}
	
	public Board(Board other) {
		 this._colSize = other._colSize;
		 this._rowSize = other._rowSize;
		 this._count = other._count; 
		 this._gameBoard = new int[other._colSize][other._rowSize];
		 
		 //deep copying the array
		 for(int i=0; i<other._gameBoard.length; i++){
			  for(int j=0; j<other._gameBoard[i].length; j++){
				  this._gameBoard[i][j]=other._gameBoard[i][j];
			  }
		 }
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _colSize;
		result = prime * result + _count;
		result = prime * result + Arrays.hashCode(_gameBoard);
		result = prime * result + _rowSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (_colSize != other._colSize)
			return false;
		if (_count != other._count)
			return false;
		if (!Arrays.deepEquals(_gameBoard, other._gameBoard))
			return false;
		if (_rowSize != other._rowSize)
			return false;
		return true;
	}

	public void setCell(BoardCell type, int col, int row){
		this._gameBoard[col][row] = type.ordinal();
	}
	
	public int getCell(int col, int row){
		return _gameBoard[col][row];
	}
	
	public int firstEmptyRow(int col) {
		//igor
		for (int i =_rowSize-1 ; i >= 0 ; i--) {
			if (this._gameBoard[col][i] == BoardCell.EMPTY.ordinal()) return i;
		}
		return -1;
	}
	
	public boolean isColFull(int col){
		if(this._gameBoard[col][0] != BoardCell.EMPTY.ordinal()) return true;
		return false;
	}
	
	public int getRowSize(){
		return this._rowSize;
	}
	
	public int getColSize(){
		return this._colSize;
	}
	
	public int getCount(){
		return this._count;
	}
	
	public int[][] getBoard(){
		return this._gameBoard;
	}
	
}
