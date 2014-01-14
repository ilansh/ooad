package FourInARow.model;

import java.util.Observable;
import java.util.Observer;

import FourInARow.board.*;
import FourInARow.view.ViewInterface;


public class FourInARowModel extends Observable implements Observer, ModelInterface{

	private int _playerTurn;
	private int DISKSTOWIN;
	private int _discsNum;
	GameState _gameState;
	private Board _board;


	//for testing - delete 
	public FourInARowModel(int colSize, int rowSize, int disksToWin){
		_board = new Board(colSize, rowSize);
		DISKSTOWIN = disksToWin+1;
	}

	//igor
	//copy c-tor
	public FourInARowModel(ModelInterface model){
		this._board = new Board(model.getBoardObject());
		this._playerTurn = model.getPlayerTurn();
		this._discsNum = model.getDiskNum();
		this.DISKSTOWIN = model.getDiskToWin();
		this._gameState = model.getGameState();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + DISKSTOWIN;
		result = prime * result + ((_board == null) ? 0 : _board.hashCode());
		result = prime * result + _discsNum;
		result = prime * result
				+ ((_gameState == null) ? 0 : _gameState.hashCode());
		result = prime * result + _playerTurn;
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
		FourInARowModel other = (FourInARowModel) obj;
		if (DISKSTOWIN != other.DISKSTOWIN)
			return false;
		if (_board == null) {
			if (other._board != null)
				return false;
		} else if (!_board.equals(other._board))
			return false;
		if (_discsNum != other._discsNum)
			return false;
		if (_gameState != other._gameState)
			return false;
		if (_playerTurn != other._playerTurn)
			return false;
		return true;
	}

	//change model state as result of the move made by on the players.
	public void update(Observable obs, Object obj) {
		int move = (int)obj;
		//System.out.println("MATHAFCKER");
		//igor
		makeMove(move);//TODO:changed here!!!
	}


	public void initGame(){

		_gameState = GameState.PROGRESS;
		_discsNum = 0;
		_playerTurn = 0;
	}


	public int getTurn(){
		return _playerTurn + 1;
	}


	public Board getBoardObject(){
		return new Board(this._board);
	}


	/*public boolean isLegalMove(int col){
		return this._board.isColFull(col);

	}*/

	public GameState getGameState(){
		return _gameState;
	}


	private void makeMove(int col){

		int row;
		//try to place a disk at requested column
		//igor
		if(col >= this._board.getColSize() || this._board.isColFull(col)){
			_gameState = GameState.ILLEGAL;
			//notifyObservers(getBoardState());
			return;
		}
		else{
			row = this._board.firstEmptyRow(col);
			this._board.setCell(BoardCell.values()[getTurn()], col, row);
			_discsNum++;
		}

		//Announce view that there us a win!
		//igor
		if(isWinnerDisk(this, col, row)){
			_gameState = GameState.WON;
			setChanged();
			notifyObservers(getBoardState());
			return;
		}
		//make draw if board is full
		else if(isBoardFull()){
			_gameState = GameState.DRAW;
			setChanged();
			notifyObservers(getBoardState());
			return;
		}
		else{
			//switch player
			_playerTurn = (_playerTurn + 1) % 2;
			//notify views of a state changed
			_gameState = GameState.PROGRESS;
			setChanged();
			notifyObservers(getBoardState());
		}

	}

	//perhaps make static for memory
	//igor
	public static boolean isWinnerDisk(FourInARowModel model, int col, int row){

		BoardCell cell = BoardCell.values()[model._board.getCell(col, row)];
		//char c = board[rowIndex][colIndex];
		int count = 1;

		// horizontal right
		for (int i= col + 1; i < model._board.getColSize(); i++) {
			if (BoardCell.values()[model._board.getCell(i, row)] == cell)
				count++;
			else break;
		}
		if (count >= model.DISKSTOWIN) return true; //horizontally

		// keep counting horizontal left
		for (int i = col-1; i >=0; i--) {
			if (BoardCell.values()[model._board.getCell(i, row)] == cell) 
				count++;
			else break;
		}
		if (count >= model.DISKSTOWIN) return true; //horizontally

		count = 1;
		// vertical down
		for (int i = row+1; i < model._board.getRowSize(); i++) {
			if (BoardCell.values()[model._board.getCell(col, i)] == cell)
				count++;
			else break;
		}
		if (count >= model.DISKSTOWIN) return true; // won vertical

		// keep counting vertical up
		for (int i=row-1; i >=0; i--) {
			if (BoardCell.values()[model._board.getCell(col, i)] == cell) 
				count++;
			else
				break;
		}
		if (count >= model.DISKSTOWIN) return true; // won vertical

		// first diagonal:  /
		count = 1;
		// up
		int kol = col+1;
		for (int i=row-1; i >= 0; i--) {
			if (kol>=model._board.getColSize()) break; // we reached the end of the board right side
			if (BoardCell.values()[model._board.getCell(kol, i)] == cell)
				count++;
			else 
				break;
			kol++;
		}
		if (count >= model.DISKSTOWIN) return true;
		// keep counting down
		kol = col-1;
		for (int i=row+1; i < model._board.getRowSize(); i++) {
			if (kol<0) break; // we reached the end of the board left side
			if (BoardCell.values()[model._board.getCell(kol, i)] == cell) 
				count++;
			else
				break;
			kol--;
		}
		if (count >= model.DISKSTOWIN) return true; // won diagonal "/"

		// second diagonal : \
		count = 1;
		// up
		kol = col-1;
		for (int i=row-1; i >= 0; i--) {
			if (kol<0) break; // we reached the end of the board left side
			if (BoardCell.values()[model._board.getCell(kol, i)] == cell)
				count++;
			else 
				break;
			kol--;
		}
		if (count >= model.DISKSTOWIN) return true; // won diagonal "\"
		// keep counting down
		kol = col+1;
		for (int i=row+1; i < model._board.getRowSize(); i++) {
			if (kol>=model._board.getColSize()) break; // we reached the end of the board right side
			if (BoardCell.values()[model._board.getCell(kol, i)] == cell) 
				count++;
			else
				break;
			kol++;
		}
		if (count >= model.DISKSTOWIN) return true; // won diagonal "\"

		return false;
	}

	private boolean isBoardFull(){
		if(this._board.getColSize()*this._board.getRowSize() == _discsNum){
			return true;
		}
		return false;
	}


	private int[][] getBoardState(){
		return _board.getBoard();
	}

	public void addLocalObserver(ViewInterface v) {
		this.addObserver((Observer) v);
	}

	public int getDiskToWin() {
		return DISKSTOWIN;
	}
	
	public int getPlayerTurn() {
		return _playerTurn;
	}
	
	public int getDiskNum() {
		return _discsNum;
	}	
}
