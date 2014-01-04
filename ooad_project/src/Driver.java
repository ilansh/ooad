

import fourInARow.controller.*;
import fourInARow.view.*;
import fourInARow.model.*;

public class Driver {
	public static void main(String[] args) {
		FourInARowModel model = new FourInARowModel(5,6);
		CellGraphic disc1 = new CellGraphic('x');
		CellGraphic disc2 = new CellGraphic('o');
		CellGraphic emptyCell = new CellGraphic(' ');
		BoardGraphic board = new BoardGraphic(disc1, disc2, emptyCell);
		FourInARowView view = new FourInARowView(board);
		FourInARowController controller = new SimpleFourInARowController(model, view);
		controller.removeView(view);
		controller.addView(view);
		controller.gameLoop();
		
	}
}
