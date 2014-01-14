



import java.awt.Point;

import fourInARow.view.IGameGraphic;




public class CellGraphic implements IGameGraphic {
	
	
	private char _cellContent;
	
	public CellGraphic(char cellContent) {
		_cellContent = cellContent;
	}
	
	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}

//	public void setContent(char cellContent) {
//		// TODO Auto-generated method stub
//		_cellContent = cellContent;
//	}
	
	@Override
	public void drawGraphic(int[][] board) {
		System.out.print(_cellContent);
		
	}
	
	@Override
	public CellGraphic clone() {
		return new CellGraphic(_cellContent);
	}
}
