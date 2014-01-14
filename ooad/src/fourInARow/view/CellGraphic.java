package fourInARow.view;

import java.awt.Point;


public class CellGraphic implements GameGraphic {
	
	private char _cellContent;
	
	public CellGraphic(char cellContent) {
		_cellContent = cellContent;
	}
	
	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.print(_cellContent);
		
	}
}
