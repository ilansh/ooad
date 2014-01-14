package fourInARow.view;

import java.awt.Point;

public class WindowGraphic extends CompositeGraphic{
	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.println("******");
		super.drawGraphic(board);
	}

}
