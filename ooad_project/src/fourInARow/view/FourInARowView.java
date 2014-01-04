package fourInARow.view;

import java.util.Observable;
import java.util.Observer;
import fourInARow.excpetion.*;

public class FourInARowView implements Observer{ //TODO: maybe singleton

	private CompositeGraphic _gameView;
	
	public FourInARowView(CompositeGraphic gameView){
		_gameView = gameView;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		_gameView.drawGraphic((int[][])arg); //TODO: is it ok???!???!??
	}
	
//	public void showView(){
//		_gameView.drawGraphic();
//	}
	
	public void showMenu(){
		
	}
	
	

	
	
	
	
}
