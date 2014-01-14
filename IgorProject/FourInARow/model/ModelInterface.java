package FourInARow.model;

import java.util.Observable;

import FourInARow.board.Board;
import FourInARow.view.ViewInterface;

public interface ModelInterface {

//	int _playerTurn = 0;
//	int _discsNum = 0;
//	int DISKSTOWIN = 0;
//	GameState _gameState = null;

	//change model state as result of the move made by on the players.
	public void update(Observable obs, Object obj);

	public void initGame();

	public Board getBoardObject();

	public GameState getGameState();

	public void addLocalObserver(ViewInterface v);

	public int getTurn();
	
	public int getDiskToWin();
	
	public int getPlayerTurn();
	
	public int getDiskNum();
}
