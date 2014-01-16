package fourInARow.view;

import java.awt.Point;
import java.util.ArrayList;

import fourInARow.model.PlayerNum;

public abstract class CompositeGraphic implements IGameGraphic {

	protected ArrayList<IGameGraphic> _graphics = new ArrayList<IGameGraphic>();
	
	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawGraphic(PlayerNum[][] board) {
		for (IGameGraphic g : _graphics ){
			g.drawGraphic(board);
		}

	}
	
	public void addGraphic(IGameGraphic graphic) {
		_graphics.add(graphic);

	}
	
	public void removeGraphic(IGameGraphic graphic) {
		_graphics.remove(graphic);

	}
	
	public abstract IGameGraphic clone();
	
	public String toString() {
		return getClass().getName();
	}
//	public GameGraphic getChild(int index) {
//		return _graphics.get(index);
//
//	}

}
