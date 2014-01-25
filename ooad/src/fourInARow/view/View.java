package fourInARow.view;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer{ //TODO: maybe singleton

	private CompositeGraphic _gameView;
	
	
	
	public View(CompositeGraphic gameView){
		_gameView = gameView;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
	
		_gameView.drawGraphic((int[][])arg); //TODO: is it ok???!???!??
	}
	
	/**
	 * 
	 * @param father - if decorating root, father should be null, otherwise, will add the decorator and remove decoratee from children
	 * @param decorator - The decorator object, wrapping the decorated object 
	 * @param decoratee - The decorated object, wrapped by the decorator
	 */
	public void decorate(CompositeGraphic father, CompositeGraphic decorator, IGameGraphic decoratee) {
		decorator.addGraphic(decoratee);
		if(father != null)
		{
			father.removeGraphic(decoratee);
			father.addGraphic(decorator);
		}
		else {
			_gameView.removeGraphic(decoratee);
			_gameView = decorator;
		}
	}
	
	@Override
	public String toString() {
		return "view containing " +  _gameView.toString();
	}

	
	
	
	
	
}
