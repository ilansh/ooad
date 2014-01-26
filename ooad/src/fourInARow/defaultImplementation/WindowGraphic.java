package fourInARow.defaultImplementation;



import fourInARow.view.CompositeGraphic;
import fourInARow.view.IGameGraphic;

public class WindowGraphic extends CompositeGraphic{

	@Override
	public void drawGraphic(int[][] board) {
		System.out.println("******");
		super.drawGraphic(board);
	}

	@Override
	public IGameGraphic clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
