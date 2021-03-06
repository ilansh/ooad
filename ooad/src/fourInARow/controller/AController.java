package fourInARow.controller;

import java.util.ArrayList;

import fourInARow.excpetion.ColumnFullException;
import fourInARow.excpetion.ColumnOutOfRangeException;
import fourInARow.model.*;
import fourInARow.player.Player;
import fourInARow.view.View;

public class AController implements IController {

	
	private static final int QUIT_KEY = 0;
	private static final int VS_HUMAN_KEY = 1;
	private static final int VS_COMPUTER_KEY = 2; //TODO: char or int
	
	
	protected MyModel _model;
	protected ArrayList<View> _views;
	protected Player _player1;
	protected Player _player2;
	protected GameStatus _gameStatus;
	protected Player _currentPlayer;
	
	public AController(MyModel model) {
		_views = new ArrayList<View>();
		_model = model;
	}
	
	private void switchTurn() {
		if(_currentPlayer.getPlayerNum() == PlayerNum.PLAYER2) {
			_currentPlayer = _player1;
		}
		else {
			_currentPlayer = _player2;
		}
	}
	
	@Override
	public GameStatus playTurn() throws ColumnFullException,
			ColumnOutOfRangeException, NumberFormatException {
		if(_gameStatus != GameStatus.ONGOING) {
			//TODO: Throw Exception
		}
		int col;
		_currentPlayer.printMoveMessage();
		col = _currentPlayer.move(_model);
		_gameStatus = _model.addDisc(col, _currentPlayer.getPlayerNum());
		switchTurn();
		return _gameStatus;
	}

	@Override
	public void mainMenu() {
		System.out.println(Integer.toString(QUIT_KEY) + ". Exit");
		System.out.println(Integer.toString(VS_HUMAN_KEY) + ". Play against a friend");
		System.out.println(Integer.toString(VS_COMPUTER_KEY) + ". Play against the computer");
		System.out.print("Please choose an option:");
	}
	

	@Override
	public void initGame(Player player1, Player player2, View view) {
		if(_views.isEmpty() || player1.getPlayerNum() == player2.getPlayerNum()) {
			//TODO: throw exception
		}
		addView(view);
		_player1 = player1;
		_player2 = player2;
		_currentPlayer = player1;
		//init view
		for(int i = 0; i < _views.size(); i++) {
			_views.get(i).update(_model, _model.getBoard());
		}
		
	}

	@Override
	public void addView(View view) {
		if(view == null) {
			return;
			//TODO" throw exception
		}
		_views.add(view);
		_model.addObserver(view);
		
	}

	@Override
	public void removeView(View view) {
		if(view == null) {
			//TODO" throw exception
		}
		_views.remove(view);
		_model.deleteObserver(view);
	}


	@Override
	public GameStatus getGameStatus(){
		return _gameStatus;
	}
	
	@Override
	public void printEndMessage() {
		if(_gameStatus == GameStatus.WIN) {
			_currentPlayer.printWinMessage();
		}
		else if(_gameStatus == GameStatus.DRAW){
			System.out.print("Board is full! game has ended with a tie!");
		}
		//TODO: throw game not over exception
	}

}
