package fourInARow.controller;

import fourInARow.excpetion.*;
import fourInARow.model.*;
import fourInARow.player.PlayerStrategy;
import fourInARow.view.View;

public interface IController {

	public GameStatus playTurn() throws ColumnFullException,
			ColumnOutOfRangeException, NumberFormatException,
			NullArgumentNotPermittedException;

	public GameStatus getGameStatus();

	public void initGame() throws NullArgumentNotPermittedException,
			TooManyPlayersException, NoViewsConfiguredException,
			NotEnoughPlayersException, Exception;

	public void addView(View view) throws NullArgumentNotPermittedException;

	public void removeView(View view) throws NullArgumentNotPermittedException;

	public void addPlayer(PlayerStrategy st, String name)
			throws NullArgumentNotPermittedException, TooManyPlayersException;

	public void printEndMessage();

}