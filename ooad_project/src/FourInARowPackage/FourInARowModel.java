package FourInARowPackage;

import java.io.ObjectInputStream.GetField;
import java.util.Observable;

public class FourInARowModel extends Observable{

	// CONSTANTS
	
	private static final int WIN = 4;
	// MEMBERS
	private int [][] _board;
	private int _cols;
	private int _rows;
	private int _discsNum;
	
	// PUBLIC METHODS
	
	public FourInARowModel(int cols, int rows) {
		_cols = cols;
		_rows = rows;
		_discsNum = 0;
		_board = new int[rows][cols];
	}
	
	public int addDisc(int col, int playerNum) throws ColumnFullException{
		int row = firstEmptyRow(col);
		_board[row][col] = playerNum;
		_discsNum++;
		
		//notify observers(views)
//		int cell[]= {row, col}; //TODO: send all the board or just the cell
		setChanged();
		notifyObservers(getBoard()); //TODO: send copy or real
	
		if (isWinner(col, row, playerNum)){
			return 1; 
		}
		else if (isBoardFull()){
			return -1;
		}
		else{
			return 0;
		}
	}
	
	public int [][] getBoard(){
		int [][] copyBoard = new int[_rows][_cols];
		for(int i=0; i<_rows; i++){
			for(int j = 0; j< _cols; j++){
				copyBoard[i][j] = _board[i][j];
			}
		}
		return copyBoard;
	}
	
	
	
	// PRIVATE METHODS

	//TODO optimize
	public boolean isWinner(int col, int row, int playerNum){
		int count = 1;

		// horizontal right
		for (int i=col+1; i < _cols; i++) {
			if (_board[row][i]==playerNum)
				count++;
			else break;
		}
		if (count >= WIN) return true; // won horizontally
		// keep counting horizontal left
		for (int i=col-1; i >=0; i--) {
			if (_board[row][i]==playerNum) 
				count++;
			else break;
		}
		if (count >= WIN) return true; // won horizontally

		count = 1;
		// vertical down
		for (int i=row+1; i < _rows; i++) {
			if (_board[i][col]==playerNum)
				count++;
			else break;
		}
		if (count >= WIN) return true; // won vertical
		// keep counting vertical up
		for (int i=row-1; i >=0; i--) {
			if (_board[i][col]==playerNum) 
				count++;
			else
				break;
		}
		if (count >= WIN) return true; // won vertical

		// first diagonal:  /
		count = 1;
		// up
		int kol = col+1;
		for (int i=row-1; i >= 0; i--) {
			if (kol>=_cols) break; // we reached the end of the _board right side
			if (_board[i][kol]==playerNum)
				count++;
			else 
				break;
			kol++;
		}
		if (count >= WIN) return true;
		// keep counting down
		kol = col-1;
		for (int i=row+1; i < _rows; i++) {
			if (kol<0) break; // we reached the end of the _board left side
			if (_board[i][kol]==playerNum) 
				count++;
			else
				break;
			kol--;
		}
		if (count >= WIN) return true; // won diagonal "/"

		// second diagonal : \
		count = 1;
		// up
		kol = col-1;
		for (int i=row-1; i >= 0; i--) {
			if (kol<0) break; // we reached the end of the _board left side
			if (_board[i][kol]==playerNum)
				count++;
			else 
				break;
			kol--;
		}
		if (count >= WIN) return true; // won diagonal "\"
		// keep counting down
		kol = col+1;
		for (int i=row+1; i < _rows; i++) {
			if (kol>=_cols) break; // we reached the end of the _board right side
			if (_board[i][kol]==playerNum) 
				count++;
			else
				break;
			kol++;
		}
		if (count >= WIN) return true; // won diagonal "\"

		return false;
	}
	
	private boolean isBoardFull(){
		if (_discsNum == _cols*_rows){
			return true;
		}
		return false;
	}
	
	private int firstEmptyRow(int col) throws ColumnFullException{
		for (int i=0; i<_board.length; i++){
			if (_board[i][col] == 0){
				return i;
			}
		}
		throw new ColumnFullException(); 
	}
	
	
}

