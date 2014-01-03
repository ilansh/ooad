package FourInARowPackage;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class FourInARowController {

	//constants
	
	protected FourInARowModel _model;
	protected ArrayList<FourInARowView> _views;
	protected ArrayList<PlayerStrategy> _players;
	
	
	protected abstract void showMenu();
	
	protected abstract void printInitMenu();
	
	protected abstract void chooseGameType(Scanner terminalInput);
	
	public abstract void gameLoop();
	
	protected void initViews() {
		for(int i = 0; i < _views.size(); i++) {
			_views.get(i).update(_model, _model.getBoard());
		}
	}
	
	
	public void addView(FourInARowView view) {
		_views.add(view);
		_model.addObserver(view);
		
	}
	
	public void removeView() { //TODO:
		
	}
	
	

		
}
