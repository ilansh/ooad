package fourInARow.view;

import java.util.ArrayList;

public abstract class CompositeGraphic implements IGameGraphic {

	protected ArrayList<IGameGraphic> _graphics = new ArrayList<IGameGraphic>();
	

	@Override
	public void drawGraphic(int[][] board) {
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

}
