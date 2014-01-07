package fourInARow.view;

public class BorderBoard  extends CompositeGraphic{

	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.println("__________________");
		super.drawGraphic(board);
	}
}
