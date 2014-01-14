package fourInARow.view;


import java.util.HashMap;
import java.awt.Point;
import fourInARow.model.PlayerNum;

public class DiscFactory {
	
	
	//Assuming each graphic is in a different location
	private HashMap<Point, IGameGraphic[]> _discPool; 
	IGameGraphic _player1Disc; 
	IGameGraphic _player2Disc;
	
	public DiscFactory (IGameGraphic player1Disc, IGameGraphic player2Disc) {
		_discPool = new HashMap<Point, IGameGraphic[]>();
		_player1Disc = player1Disc.clone();
		_player2Disc = player2Disc.clone();
	}
	
	
	private IGameGraphic getCell(Point p, int playerNum) {
		IGameGraphic retCell = null;
		if(playerNum == PlayerNum.PLAYER1.ordinal() - 1) {				
			retCell = _player1Disc.clone();
		}
		else if (playerNum == PlayerNum.PLAYER2.ordinal() - 1){
			retCell = _player2Disc.clone();
		}
		else {
			//TODO: throw exception
		}
		retCell.setLocation(p);
		return retCell;

	}
	
	public IGameGraphic newInstance(int row, int column, int playerNum) {
		Point p = new Point(row, column);
		IGameGraphic retCell; 
		playerNum = playerNum - 1; //to adjust for array
		if(_discPool.containsKey(p) && _discPool.get(p)[playerNum] != null) { //The desired graphic is in the pool
			retCell = _discPool.get(p)[playerNum];
		}
		else {  //The desired graphic isn't in the pool
			retCell = getCell(p, playerNum);
			if (_discPool.containsKey(p)) {  //There is another graphic at location p in the pool.
				_discPool.get(p)[playerNum] =  retCell;  //add cell to pool
			}
			else { //The location doesn't exist in the pool
				IGameGraphic[] cellArr = new IGameGraphic[2];
				cellArr[playerNum] = retCell;
				_discPool.put(p, cellArr);
			}
		}
		return retCell;
	}
	
}
