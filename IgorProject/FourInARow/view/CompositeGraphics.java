package FourInARow.view;

import java.util.ArrayList;

public class CompositeGraphics implements Graphics {

	ArrayList<Graphics> _graphics;
	
	
	public CompositeGraphics(){
		_graphics = new ArrayList<Graphics>();
	}
	
	
	@Override
	public Object drawGraphics(int[][] board) {//aspectJ here
		for (Graphics g : _graphics ){
			g.drawGraphics(board);
		}
		//return board;
		return null;
	}
	
	public void addGraphics(Graphics graphic) {
		
		_graphics.add(graphic);

	}
	
	public Graphics getGraphics(int index) {
		return _graphics.get(index);

	}

}
