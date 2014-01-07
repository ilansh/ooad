
package fourInARow.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import fourInARow.model.*;
import fourInARow.view.FourInARowView;
import fourInARow.excpetion.*;

public interface GameController {

	//constants
	
//	protected FourInARowModel _model;
//	protected ArrayList<FourInARowView> _views;
//	protected ArrayList<PlayerStrategy> _players;
//		
	
	public GameStatus playTurn(); //don't call gameLoop before initMenu, use key
	
	public void mainMenu();
	
	public GameStatus getGameStatus();
	
	public void initViews();
	
	
	public void addView(FourInARowView view);
	
	public void removeView(FourInARowView view);
	

	public void printEndMessage();
		
}