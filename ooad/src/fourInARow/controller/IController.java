
package fourInARow.controller;


import fourInARow.model.*;
import fourInARow.view.View;

public interface IController {
	
	public GameStatus playTurn(); //don't call gameLoop before initMenu, use key
	
	public void mainMenu();
	
	public GameStatus getGameStatus();
	
	public void initViews();
	
	public void addView(View view);
	
	public void removeView(View view);
	
	public void printEndMessage();
		
}