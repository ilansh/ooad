package FourInARow.view;


import java.util.Observable;
import java.util.Observer;

//import fourInARow.excpetion.*;

public class FourInARowView implements Observer, ViewInterface{ 
	

	private Graphics _gameView;
	
	
	
	public FourInARowView(Graphics gameGraphics){
		_gameView = gameGraphics;
	}
	
		
	
	@Override
	public void update(Observable obs, Object obj) {
		//System.out.println("BANANA");//TODO: aspectJ here
		_gameView.drawGraphics((int[][])obj); 
		
	}
	
	
		
	/*public void displayView(){
		_gameView.drawGraphics();
	}*/
	
	
	
}
