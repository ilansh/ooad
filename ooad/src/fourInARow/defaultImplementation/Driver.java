package fourInARow.defaultImplementation;

import java.io.PrintWriter;
import fourInARow.aspects.GameLogger;
import fourInARow.controller.*;
import fourInARow.excpetion.*;
import fourInARow.view.*;
import fourInARow.loggingProxy.LoggingProxy;
import fourInARow.model.*;

public class Driver {

	public static final String LOG_ENABLED = "--log";

	// GameLogger gl;

	public static void runGame(IController controller, IModel model)
			throws NullArgumentNotPermittedException {

		try {
			controller.initGame();
		} catch (TooManyPlayersEception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoViewsConfiguredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughPlayersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			}
		} while (status != GameStatus.WIN && status != GameStatus.DRAW);

		controller.printEndMessage();

	}

	public static void main(String[] args) throws Exception{

		// TODO: Do you want to log method calls (do through run arguments)
		PrintWriter controllerLog = null;
		PrintWriter modelLog = null;
		PrintWriter gameLog = null;
		
		controllerLog = new PrintWriter("contoller.log", "UTF-8");
		modelLog = new PrintWriter("model.log", "UTF-8");
		gameLog = new PrintWriter("game.log", "UTF-8");
		

		MyModel model = new MyModel(5, 6);
		IModel loggedModel = (IModel)LoggingProxy.newInstance(model, modelLog);
		IController controller = null;
		try {
			controller = new MyController(loggedModel);
		} catch (NullArgumentNotPermittedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		

		try {
		// if(args.length > 0 && args[0].equalsIgnoreCase(LOG_ENABLED)) {

			// GameLogger gl = GameLogger.aspectOf();
			// gl.initLogger(gameLog);
			GameLogger.initLogStream(gameLog);
//			 IController loggedController =
//					 			(IController)LoggingProxy.newInstance(controller, controllerLog);
			
			runGame(controller, loggedModel);
			controllerLog.close();
			modelLog.close();
			gameLog.close();
		} 
		catch (NullArgumentNotPermittedException nanpe) {
			System.out.println("Null arguments not permittes");// TODO exit?
		}
		// }
		// else {
		// runGame(controller, model);
		// }

		System.exit(0);

	}
}
