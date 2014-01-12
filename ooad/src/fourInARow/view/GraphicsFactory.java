package fourInARow.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class GraphicsFactory {
	
	
	//Assuming each graphic is in a different location
	private HashMap<Point, GameGraphic> _boardPool;
	private HashMap<Point, GameGraphic> _cellPool;
	
	public GraphicsFactory () {
		_boardPool = new HashMap<Point, GameGraphic>();
		_cellPool = new HashMap<Point, GameGraphic>();
	}
	
	public GameGraphic newBoard(Point p) {
		if(_boardPool.get(p) != null ) {
			return _boardPool.get(p);
		}
		GameGraphic board = new BoardGraphic();
		_boardPool.put(p, board);
		return board;
	}
	
	public GameGraphic getGraphic(Point p) {
		if(_cellPool.get(p) != null ) {
			return _cellPool.get(p);
		}
		GameGraphic cell = new CellGraphic();
		_boardPool.put(p, cell);
		return cell;
	}
	
}
