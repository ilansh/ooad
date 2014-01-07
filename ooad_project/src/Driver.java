

import fourInARow.controller.*;
import fourInARow.view.*;
import fourInARow.model.*;

public class Driver {
	public static void main(String[] args) {
		FourInARowModel model = new FourInARowModel(5,6);
		FourInARowController controller = new SimpleFourInARowController(model);
		controller.gameLoop();
		
		
	}
}
