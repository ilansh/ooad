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

	/*
	 * A method for the client to implement
	 * This method is called  from the initGame method in a loop until a true value is returned.
	 */
	protected abstract boolean mainMenu() throws Exception;

	/**
	 * play a single turn in the game.
	 * This method should be called only after initGame.
	 * @return The game status after this turn has been played.
	 * @throws ColumnFullException
	 * @throws ColumnOutOfRangeException
	 * @throws NumberFormatException
	 * @throws NullArgumentNotPermittedException
	 * @throws GameNotOngoingException
	 */
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

	/**
	 *  Initialize the game.
	 *  This method calls the mainMenu method implemented by the client,
	 *  confirms that two players are set, and initializes the views.
	 * @throws NullArgumentNotPermittedException
	 * @throws TooManyPlayersException
	 * @throws NoViewsConfiguredException
	 * @throws NotEnoughPlayersException
	 * @throws Exception
	 */
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

	/**
	 * Add a view to the game.
	 * @param view The view to add
	 * @throws NullArgumentNotPermittedException
	 */
	public void addView(View view) throws NullArgumentNotPermittedException {
		if (view == null) {
			throw new NullArgumentNotPermittedException();
		}
		_views.add(view);
		_model.getObservable().addObserver(view);

	}

	/**
	 * Remove a view from the game
	 * @param view the view to remove
	 * @throws NullArgumentNotPermittedException
	 */
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

	/**
	 * To be called once the game is over, printing the result of the game.
	 */
	public void printEndMessage() {
		if (_gameStatus == GameStatus.WIN) {
			_currentPlayer.printWinMessage();
		} else if (_gameStatus == GameStatus.DRAW) {
			System.out.print("Board is full! game has ended with a tie!");
		} else {
			System.out.print("Game not over yet!");
		}
	}

	/**
	 * Add a player with the given strategy and name to the game.
	 * This method should be called exactly twice before initializing the game (before initGame)
	 * @param st PlayerStrategy implementation.
	 * @param name Player's name
	 * @throws NullArgumentNotPermittedException
	 * @throws TooManyPlayersException
	 */
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
