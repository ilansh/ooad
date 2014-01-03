package FourInARowPackage;

import java.util.Observable;
import java.util.Observer;

public class FourInARowView implements Observer{ //TODO: maybe singleton

	private CompositeGraphic _gameView;
	
	public FourInARowView(CompositeGraphic gameView){
		_gameView = gameView;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Model has changed");
		_gameView.drawGraphic((int[][])arg); //TODO: is it ok???!???!??
		
	}
	
//	public void showView(){
//		_gameView.drawGraphic();
//	}
	
	public void showMenu(){
		
	}
	
	

	
	
	
	
}
