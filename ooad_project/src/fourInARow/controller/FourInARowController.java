
package fourInARow.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import fourInARow.model.FourInARowModel;
import fourInARow.view.FourInARowView;
import fourInARow.excpetion.*;

public abstract class FourInARowController {

	//constants
	
	protected FourInARowModel _model;
	protected ArrayList<FourInARowView> _views;
	protected ArrayList<PlayerStrategy> _players;
	
		
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
	
	public void removeView(FourInARowView view) {
		_views.remove(view);
		_model.deleteObserver(view);
	}
		
}
