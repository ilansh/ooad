package FourInARowPackage;

public class CellGraphic implements GameGraphic {
	
	private char _cellContent;
	
	public CellGraphic(char cellContent) {
		_cellContent = cellContent;
	}
	
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.print(_cellContent);
		
	}
}
