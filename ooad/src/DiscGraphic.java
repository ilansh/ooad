



import java.awt.Point;

import fourInARow.model.PlayerNum;
import fourInARow.view.IGameGraphic;




public class DiscGraphic implements IGameGraphic {
	
	
	private char _cellContent;
	
	public DiscGraphic(char cellContent) {
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
	public void drawGraphic(PlayerNum[][] board) {
		System.out.print(_cellContent);
		
	}
	
	@Override
	public DiscGraphic clone() {
		return new DiscGraphic(_cellContent);
	}
}
