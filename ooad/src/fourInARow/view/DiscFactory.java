package fourInARow.view;


import java.util.HashMap;
import java.awt.Point;

import fourInARow.excpetion.InvalidPlayerNumException;

/**
 * concrete factories are created dynamically.
 *
 */
public class DiscFactory {
	
	
	private IGameGraphic _disc1;
	private IGameGraphic _disc2;
	
	public DiscFactory (IGameGraphic disc1, IGameGraphic disc2) {
		_disc1 = disc1;
		_disc2 = disc2;
	}
	
	
	public IGameGraphic getDisc(int playerNum) throws InvalidPlayerNumException {
		IGameGraphic retDisc = null; 
		if(playerNum == 1) { 
			retDisc = _disc1;
		}
		else if(playerNum == 2) { 
			retDisc = _disc2;
		}
		else {
			throw new InvalidPlayerNumException();
		}
		return retDisc;
	}
	
}
