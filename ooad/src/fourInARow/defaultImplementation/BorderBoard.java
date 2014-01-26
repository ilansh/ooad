package fourInARow.defaultImplementation;


import fourInARow.view.CompositeGraphic;
import fourInARow.view.IGameGraphic;

public class BorderBoard extends CompositeGraphic {


	@Override
	public void drawGraphic(int[][] board) {
		System.out.println("__________________");
		super.drawGraphic(board); //draw children
	}

	@Override
	public IGameGraphic clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
