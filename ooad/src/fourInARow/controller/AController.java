package fourInARow.controller;

import java.util.ArrayList;
import fourInARow.controller.IController;
import fourInARow.excpetion.*;
import fourInARow.model.*;
import fourInARow.player.*;
import fourInARow.view.*;

public abstract class AController implements IController {

	protected IModel _model;
	protected ArrayList<View> _views;
	protected IPlayer _player1;
	protected IPlayer _player2;
	protected IPlayer _currentPlayer;

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

	@Override
	public GameStatus playTurn() throws ColumnFullException,
			ColumnOutOfRangeException, NumberFormatException,
			NullArgumentNotPermittedException {
		if (_gameStatus != GameStatus.ONGOING) {
			// TODO: Throw Exception
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

	@Override
	public void initGame() throws NullArgumentNotPermittedException,
			TooManyPlayersEception, NoViewsConfiguredException,
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

	@Override
	public void addView(View view) throws NullArgumentNotPermittedException {
		if (view == null) {
			throw new NullArgumentNotPermittedException();
		}
		_views.add(view);
		_model.getObservable().addObserver(view);

	}

	@Override
	public void removeView(View view) throws NullArgumentNotPermittedException {
		if (view == null) {
			throw new NullArgumentNotPermittedException();
		}
		_views.remove(view);
		_model.getObservable().deleteObserver(view);
	}

	@Override
	public GameStatus getGameStatus() {
		return _gameStatus;
	}

	@Override
	public void printEndMessage() {
		if (_gameStatus == GameStatus.WIN) {
			_currentPlayer.printWinMessage();
		} else if (_gameStatus == GameStatus.DRAW) {
			System.out.print("Board is full! game has ended with a tie!");
		} else {
			System.out.print("Game not over yet!");
		}
	}

	@Override
	public void addPlayer(PlayerStrategy st, String name)
			throws NullArgumentNotPermittedException, TooManyPlayersEception {
		if (_player1 == null) {
			_player1 = new Player(st, name, 1);
		} else if (_player2 == null) {
			_player2 = new Player(st, name, 2);
		} else {
			throw new TooManyPlayersEception();
		}
	}

}
