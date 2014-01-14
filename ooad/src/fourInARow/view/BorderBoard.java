package fourInARow.view;

import java.awt.Point;

public class BorderBoard  extends CompositeGraphic{

	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.println("__________________");
		super.drawGraphic(board);
	}
}
