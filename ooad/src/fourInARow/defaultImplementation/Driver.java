package fourInARow.defaultImplementation;

import java.io.PrintWriter;
import fourInARow.aspects.GameLogger;
import fourInARow.controller.*;
import fourInARow.excpetion.*;
import fourInARow.loggingProxy.LoggingProxy;
import fourInARow.model.*;

public class Driver {


	public static void runGame(AController controller, IModel model)
			throws Exception {

		controller.initGame();
	

		GameStatus status = controller.getGameStatus();

		do {
			try {
				status = controller.playTurn();
			} catch (ColumnFullException cfe) {
				System.out.println("Column is full");
			} catch (ColumnOutOfRangeException coore) {
				System.out.println("Column out of range");
			} catch (NumberFormatException nfe) { // makemove by human
				System.out.println("illegal column format");
			} catch (GameNotOngoingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (status != GameStatus.WIN && status != GameStatus.DRAW);

		controller.printEndMessage();

	}

	
	public static void main(String[] args) throws Exception{


		PrintWriter modelLog = new PrintWriter("model.log", "UTF-8");
		PrintWriter gameLog = new PrintWriter("game.log", "UTF-8");
		GameLogger.initLogStream(gameLog);

		MyModel model = new MyModel(7, 6);
		IModel loggedModel = (IModel)LoggingProxy.newInstance(model, modelLog);
     	AController controller = new MyController(loggedModel);
				
		runGame(controller, loggedModel);
		
		modelLog.close();
		gameLog.close();


		System.exit(0);

	}
}
