package FourInARowPackage;

import java.util.ArrayList;

public abstract class FourInARowController {

	protected FourInARowModel _model;
	protected ArrayList<FourInARowView> _views;
	// TODO _players = strategy
	
	
	abstract public void showMenu();
	
	public void gameLoop(){
		
	}
}
