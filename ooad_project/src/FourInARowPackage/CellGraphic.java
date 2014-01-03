package FourInARowPackage;

public class CellGraphic implements GameGraphic {
	
	private static final char DISC1_GRAPHIC = 'x';
	
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.print(DISC1_GRAPHIC);
		
	}
}
