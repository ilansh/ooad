
package fourInARow.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import fourInARow.model.FourInARowModel;
import fourInARow.view.FourInARowView;
import fourInARow.excpetion.*;

public interface GameController {

	//constants
	
//	protected FourInARowModel _model;
//	protected ArrayList<FourInARowView> _views;
//	protected ArrayList<PlayerStrategy> _players;
//		
	
	public void playTurn(); //don't call gameLoop before initMenu, use key
	
	public int mainMenu();
	
	public void isGameOver();
	
	public void initViews();
	
	
	public void addView(FourInARowView view);
	
	public void removeView(FourInARowView view);
		
}