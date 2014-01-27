package fourInARow.controller;

import java.util.ArrayList;
import fourInARow.excpetion.*;
import fourInARow.model.*;
import fourInARow.player.*;
import fourInARow.view.*;

public abstract class AController {

	protected IModel _model;
	protected ArrayList<View> _views;
	protected Player _player1;
	protected Player _player2;
	protected Player _currentPlayer;

	protected GameStatus _gameStatus;

	protected AController(IModel model) throws NullArgumentNotPermittedException {
		if(model == null ) {
			throw new NullArgumentNotPermittedException();
		}
		_views = new ArrayList<View>();
		_model = model;
		_gameStatus = GameStatus.NOT_INIT;
	}

	private void switchTurn() {
		if (_currentPlayer.getPlayerNum() == 2) {
			_currentPlayer = _player1;
		} else {
			_currentPlayer = _player2;
		}
	}

	protected abstract boolean mainMenu() throws Exception;

	public GameStatus playTurn() throws ColumnFullException,
			ColumnOutOfRangeException, NumberFormatException,
			NullArgumentNotPermittedException, GameNotOngoingException {
		if (_gameStatus != GameStatus.ONGOING) {
			throw new GameNotOngoingException();
		}
		int col;
		_currentPlayer.printMoveMessage();
		col = _currentPlayer.move(_model);
		_gameStatus = _model.addDisc(col, _currentPlayer.getPlayerNum());
		if(_gameStatus != GameStatus.WIN) {
			switchTurn();
		}
		return _gameStatus;
	}

	public void initGame() throws NullArgumentNotPermittedException,
			TooManyPlayersException, NoViewsConfiguredException,
			NotEnoughPlayersException, Exception {
		if (_views.isEmpty()) {
			throw new NoViewsConfiguredException();
		}
		boolean toStart = mainMenu();
		while(!toStart) {
			toStart = mainMenu();
		}
		if (_player1 == null || _player2 == null) {
			throw new NotEnoughPlayersException();
		}
		_currentPlayer = _player1;
		for (int i = 0; i < _views.size(); i++) {
			_views.get(i).update(_model.getObservable(), _model.getBoard());
		}
		_gameStatus = GameStatus.ONGOING;
	}

	public void addView(View view) throws NullArgumentNotPermittedException {
		if (view == null) {
			throw new NullArgumentNotPermittedException();
		}
		_views.add(view);
		_model.getObservable().addObserver(view);

	}

	public void removeView(View view) throws NullArgumentNotPermittedException {
		if (view == null) {
			throw new NullArgumentNotPermittedException();
		}
		_views.remove(view);
		_model.getObservable().deleteObserver(view);
	}

	public GameStatus getGameStatus() {
		return _gameStatus;
	}

	public void printEndMessage() {
		if (_gameStatus == GameStatus.WIN) {
			_currentPlayer.printWinMessage();
		} else if (_gameStatus == GameStatus.DRAW) {
			System.out.print("Board is full! game has ended with a tie!");
		} else {
			System.out.print("Game not over yet!");
		}
	}

	public void addPlayer(PlayerStrategy st, String name)
			throws NullArgumentNotPermittedException, TooManyPlayersException {
		if (_player1 == null) {
			_player1 = new Player(st, name, 1);
		} else if (_player2 == null) {
			_player2 = new Player(st, name, 2);
		} else {
			throw new TooManyPlayersException();
		}
	}

}
