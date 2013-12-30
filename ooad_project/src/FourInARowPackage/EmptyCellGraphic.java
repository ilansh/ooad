package FourInARowPackage;

public class EmptyCellGraphic implements GameGraphic {

	private static final char EMPTY_CELL_GRAPHIC = ' ';
	
	
	public EmptyCellGraphic(){
		
	}
	
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.print(EMPTY_CELL_GRAPHIC);
		
	}


}
