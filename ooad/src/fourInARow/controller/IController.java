
package fourInARow.controller;


import fourInARow.excpetion.*;
import fourInARow.model.*;
import fourInARow.view.View;

public interface IController {
	
	public GameStatus playTurn() throws ColumnFullException, ColumnOutOfRangeException, NumberFormatException, NullArgumentNotPermittedException;
	
	public void mainMenu() throws NullArgumentNotPermittedException;
	
	public GameStatus getGameStatus();
	
	public void initViews();
	
	public void addView(View view);
	
	public void removeView(View view);
	
	public void printEndMessage();

		
}