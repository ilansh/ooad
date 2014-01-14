package fourInARow.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import fourInARow.model.PlayerNum;

public class CellFactory {
	
	
	//Assuming each graphic is in a different location
	private HashMap<Point, IGameGraphic[]> _cellPool; 
	IGameGraphic _emptyCell;
	IGameGraphic _player1Disc; 
	IGameGraphic _player2Disc;
	
	public CellFactory (IGameGraphic emptyCell, IGameGraphic player1Disc, IGameGraphic player2Disc) {
		_cellPool = new HashMap<Point, IGameGraphic[]>();
		_emptyCell = emptyCell.clone();
		_player1Disc = player1Disc.clone();
		_player2Disc = player2Disc.clone();
	}
	
	
	private IGameGraphic getCell(Point p, int playerNum) {
		IGameGraphic retCell = null;
		if(playerNum == PlayerNum.EMPTY.ordinal()) {				
			retCell = _emptyCell.clone();
		}
		else if(playerNum == PlayerNum.PLAYER1.ordinal()) {				
			retCell = _player1Disc.clone();
		}
		else if (playerNum == PlayerNum.PLAYER2.ordinal()){
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
		if(_cellPool.containsKey(p) && _cellPool.get(p)[playerNum] != null) { //The desired graphic is in the pool
			retCell = _cellPool.get(p)[playerNum];
		}
		else {  //The desired graphic isn't in the pool
			retCell = getCell(p, playerNum);
			if (_cellPool.containsKey(p)) {  //There is another graphic at location p in the pool.
				_cellPool.get(p)[playerNum] =  retCell;  //add cell to pool
			}
			else { //The location doesn't exist in the pool
				IGameGraphic[] cellArr = new IGameGraphic[3];
				cellArr[playerNum] = retCell;
				_cellPool.put(p, cellArr);
			}
		}
		return retCell;
	}
	
}
