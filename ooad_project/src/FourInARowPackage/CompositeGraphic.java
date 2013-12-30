package FourInARowPackage;

import java.util.ArrayList;

public abstract class CompositeGraphic implements GameGraphic {

	ArrayList<GameGraphic> _graphics;
	
	@Override
	public void setLocation(int x, int y) {
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
	
	public void removeGraphic(int index) {
		_graphics.remove(index);

	}
	
	public GameGraphic getChild(int index) {
		return _graphics.get(index);

	}

}
