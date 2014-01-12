

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fourInARow.aspects.GameLogger;
import fourInARow.controller.*;
import fourInARow.view.*;
import fourInARow.model.*;
import fourInARow.loggingProxy.*;


public class Driver {
	
	public static final String LOG_ENABLED = "--log";
	
	
//	GameLogger gl;
	
	public static void runGame(IController controller, MyModel model) {
		controller.mainMenu();
		controller.initViews();
		
		View view = new View(new WindowGraphic());
	
		controller.addView(view);
		
		GameStatus status = controller.getGameStatus();
		
		do{
			status = controller.playTurn();
		} while(status != GameStatus.WIN && status != GameStatus.DRAW);
	
		controller.printEndMessage();
		
		
	}
	
	public static void main(String[] args) {
		
		//TODO: Do you want to log method calls (do through run arguments)
		PrintWriter controllerLog = null;
		PrintWriter modelLog = null;
		PrintWriter gameLog = null;
		
		
		MyModel model = new MyModel(5, 6);
		IController controller = new MyController(model);
		
//		if(args.length > 0 && args[0].equalsIgnoreCase(LOG_ENABLED)) {
			try {
				controllerLog = new PrintWriter("contoller.log", "UTF-8");
				modelLog = new PrintWriter("model.log", "UTF-8");
				gameLog = new PrintWriter("game.log", "UTF-8");
				GameLogger gl = GameLogger.aspectOf();
				gl.initLogger(gameLog);
				IController loggedController = (IController)LoggingProxy.newInstance(controller, controllerLog);
				runGame(loggedController, model);
				controllerLog.close();
				modelLog.close();
				gameLog.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
//		else {
//			runGame(controller, model);
//		}
		
		System.exit(0);
		
		
	}
}