package fourInARow.view;

import java.awt.Point;
import java.util.ArrayList;

public abstract class CompositeGraphic implements GameGraphic {

	ArrayList<GameGraphic> _graphics = new ArrayList<GameGraphic>();
	
	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawGraphic(int[][] board) {
		for (GameGraphic g : _graphics ){
			g.drawGraphic(board);
		}

	}
	
	public void addGraphic(GameGraphic graphic) {
		_graphics.add(graphic);

	}
	
	public void removeGraphic(GameGraphic graphic) {
		_graphics.remove(graphic);

	}
	
//	public GameGraphic getChild(int index) {
//		return _graphics.get(index);
//
//	}

}
