
package FourInARow.controller;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
//import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;
//import FourInARow.view.FourInARowView;
import FourInARow.view.ViewInterface;


public abstract class FourInARowController extends Observable implements ControllerInterface{

	protected ModelInterface _model;
	protected Strategy _playerOne, _playerTwo; 
	protected ArrayList<ViewInterface> _views;
	
	
	public FourInARowController(ModelInterface myModel, ArrayList<ViewInterface> views){
		_model = myModel;
		
		_views = new ArrayList<ViewInterface>();
		for(int i = 0; i < views.size(); i++) {
			_views.add(views.get(i));
		}
	}
	
	
	protected void initModel(){
		_model.initGame();
	} 
	
	
	public abstract void startGame(Scanner sc1, Scanner sc2);
	
	public ModelInterface getModel(){
		return _model;
	}

	public Strategy getPlyerOne(){
		return _playerOne;
	}
	
	public Strategy getPlyerTwo(){
		return _playerTwo;
	}

	public ArrayList<ViewInterface> getViews(){
		return _views;
	}

}
