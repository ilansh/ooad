package fourInARow.view;


import java.util.HashMap;
import java.awt.Point;

/**
 * concrete factories are created dynamically.
 *
 */
public class DiscFactory {
	
	
	//Assuming each graphic is in a different location
	private HashMap<Point, IGameGraphic> _discPool; 
	private IGameGraphic _disc;
	
	public DiscFactory (IGameGraphic disc) {
		_discPool = new HashMap<Point, IGameGraphic>();
		_disc = disc.clone();
	}
	
	
	public IGameGraphic getDisc(int row, int column, int playerNum) {
		Point p = new Point(row, column);
		IGameGraphic retDisc; 
		if(_discPool.containsKey(p)) { //The desired graphic is in the pool
			retDisc = _discPool.get(p);
		}
		else {  //The desired graphic isn't in the pool
			retDisc = _disc.clone();
			retDisc.setLocation(p);
			_discPool.put(p, retDisc);
		}
		return retDisc;
	}
	
}
