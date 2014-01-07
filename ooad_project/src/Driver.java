

import fourInARow.controller.*;
import fourInARow.view.*;
import fourInARow.model.*;

public class Driver {
	public static void main(String[] args) {
		FourInARowModel model = new FourInARowModel(5,6);
		GameController controller = new MyGame(model);
		
		
		controller.mainMenu();
		controller.initViews();
		
		GameStatus status = controller.getGameStatus();
		
		do{
			status = controller.playTurn();
		} while(status != GameStatus.WIN && status != GameStatus.DRAW);
	
		controller.printEndMessage();
	}
}
