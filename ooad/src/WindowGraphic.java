

import java.awt.Point;

import fourInARow.model.PlayerNum;
import fourInARow.view.CompositeGraphic;
import fourInARow.view.IGameGraphic;

public class WindowGraphic extends CompositeGraphic{
	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(PlayerNum[][] board) {
		System.out.println("******");
		super.drawGraphic(board);
	}

	@Override
	public IGameGraphic clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
