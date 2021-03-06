package fourInARow.model;


import java.util.Observable;
import fourInARow.excpetion.*;

public class MyModel extends Observable implements IModel{ //TODO: handle GameModel who is not observable

	// CONSTANTS

	private static final int WIN_COUNT = 4;
	
	
	
	
	// MEMBERS
	private PlayerNum [][] _board;
	private int _cols;
	private int _rows;
	private int _discsNum;
//	private GameStatus _gameStatus;
	
	// PUBLIC METHODS
	
	public MyModel(int cols, int rows) {
		_cols = cols;
		_rows = rows;
		_discsNum = 0;
		_board = new PlayerNum[rows][cols];
//		_gameStatus = GameStatus.ONGOING;

	}
	
	
	
	
	/**
	 * 
	 * @param col
	 * @param playerNum
	 * @return
	 * @throws ColumnFullException
	 */
	public GameStatus addDisc(int col, PlayerNum playerNum) throws ColumnFullException , ColumnOutOfRangeException{ //TODO: exception heirarchy
		isColOutOfRange(col);
		
		int row = firstEmptyRow(col);
		_board[row][col] = playerNum;
		_discsNum++;
		
//		int cell[]= {row, col}; //TODO: send all the board or just the cell
		setChanged();
		notifyObservers(getBoard()); //TODO: send copy or real
	
		if (isWinner(_board, col, row, playerNum)){
			return GameStatus.WIN; 
		}
		else if (isBoardFull()){
			return GameStatus.DRAW;
		}
		return GameStatus.ONGOING;
	}
	
	public PlayerNum [][] getBoard(){
		PlayerNum [][] copyBoard = new PlayerNum[_rows][_cols];
		for(int i=0; i<_rows; i++){
			for(int j = 0; j< _cols; j++){
				copyBoard[i][j] = _board[i][j];
			}
		}
		return copyBoard;
	}
	
	
	public int getNumRows() {
		return _rows;
	}
	
	public int getNumCols() {
		return _cols;
	}
	
	
	//TODO optimize
	public boolean isWinner(PlayerNum[][] board, int col, int row, PlayerNum playerNum){
		int count = 1;

		// horizontal right
		for (int i=col+1; i < _cols; i++) {
			if (board[row][i]==playerNum)
				count++;
			else break;
		}
		if (count >= WIN_COUNT) return true; // won horizontally
		// keep counting horizontal left
		for (int i=col-1; i >=0; i--) {
			if (board[row][i]==playerNum) 
				count++;
			else break;
		}
		if (count >= WIN_COUNT) return true; // won horizontally

		count = 1;
		// vertical down
		for (int i=row+1; i < _rows; i++) {
			if (board[i][col]==playerNum)
				count++;
			else break;
		}
		if (count >= WIN_COUNT) return true; // won vertical
		// keep counting vertical up
		for (int i=row-1; i >=0; i--) {
			if (board[i][col]==playerNum) 
				count++;
			else
				break;
		}
		if (count >= WIN_COUNT) return true; // won vertical

		// first diagonal:  /
		count = 1;
		// up
		int kol = col+1;
		for (int i=row-1; i >= 0; i--) {
			if (kol>=_cols) break; // we reached the end of the _board right side
			if (board[i][kol]==playerNum)
				count++;
			else 
				break;
			kol++;
		}
		if (count >= WIN_COUNT) return true;
		// keep counting down
		kol = col-1;
		for (int i=row+1; i < _rows; i++) {
			if (kol<0) break; // we reached the end of the _board left side
			if (board[i][kol]==playerNum) 
				count++;
			else
				break;
			kol--;
		}
		if (count >= WIN_COUNT) return true; // won diagonal "/"

		// second diagonal : \
		count = 1;
		// up
		kol = col-1;
		for (int i=row-1; i >= 0; i--) {
			if (kol<0) break; // we reached the end of the _board left side
			if (board[i][kol]==playerNum)
				count++;
			else 
				break;
			kol--;
		}
		if (count >= WIN_COUNT) return true; // won diagonal "\"
		// keep counting down
		kol = col+1;
		for (int i=row+1; i < _rows; i++) {
			if (kol>=_cols) break; // we reached the end of the _board right side
			if (board[i][kol]==playerNum) 
				count++;
			else
				break;
			kol++;
		}
		if (count >= WIN_COUNT) return true; // won diagonal "\"

		return false;
	}

	public int firstEmptyRow(int col) throws ColumnFullException{
		for (int i = _rows - 1; i >= 0; i--){
			if (_board[i][col] == PlayerNum.EMPTY){
				return i;
			}
		}
		throw new ColumnFullException(); 
	}
	
	// PRIVATE METHODS
	
	private boolean isBoardFull(){
		if (_discsNum == _cols*_rows){
			return true;
		}
		return false;
	}
	
	
	private void isColOutOfRange(int col) throws ColumnOutOfRangeException {
		if(col < 0 || col >= _cols) {
			throw new ColumnOutOfRangeException();
		}
	}
}

