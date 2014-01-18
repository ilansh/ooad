package fourInARow.defaultImplementation;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fourInARow.aspects.GameLogger;
import fourInARow.controller.*;
import fourInARow.excpetion.ColumnFullException;
import fourInARow.excpetion.ColumnOutOfRangeException;
import fourInARow.excpetion.NullArgumentNotPermittedException;
import fourInARow.view.*;
import fourInARow.model.*;
import fourInARow.loggingProxy.*;


public class Driver {
	
	public static final String LOG_ENABLED = "--log";
	
	
//	GameLogger gl;
	
	public static void runGame(IController controller, MyModel model, View view) throws NullArgumentNotPermittedException {

		
	
		
		controller.addView(view);
		
		controller.mainMenu();
		controller.initViews();
		
		GameStatus status = controller.getGameStatus();
		
		do{
			try {
				status = controller.playTurn();
			}
			catch(ColumnFullException cfe) {
				System.out.println("Column is full");
			}
			catch(ColumnOutOfRangeException coore) {
				System.out.println("Column out of range");
			}
			catch(NumberFormatException nfe) { //makemove by human
				System.out.println("illegal column format");
			}
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
		
		DiscGraphic disc1 = new DiscGraphic('x');
		DiscGraphic disc2 = new DiscGraphic('o');
		
		
		AbstractDiscFactory adf = AbstractDiscFactory.newInsance();
		adf.addFactoryImpl(disc1);
		adf.addFactoryImpl(disc2);
		DiscFactory df1 = adf.getFactory(disc1);
		DiscFactory df2 = adf.getFactory(disc2);
		BoardGraphic board = new BoardGraphic(df1, df2);
		
		WindowGraphic window = new WindowGraphic(); //TODO" remove this
		window.addGraphic(board);
		
		BorderBoard b = new BorderBoard();
		
		View view = new View(window);
//		view.decorate(null, b, window);
		
//		controller.addView(new View(board));
		
//		if(args.length > 0 && args[0].equalsIgnoreCase(LOG_ENABLED)) {
			try {
				controllerLog = new PrintWriter("contoller.log", "UTF-8");
				modelLog = new PrintWriter("model.log", "UTF-8");
				gameLog = new PrintWriter("game.log", "UTF-8");
				GameLogger gl = GameLogger.aspectOf();
				gl.initLogger(gameLog);
				//IController loggedController = (IController)LoggingProxy.newInstance(controller, controllerLog);
				runGame(controller, model, view);
				controllerLog.close();
				modelLog.close();
				gameLog.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(NullArgumentNotPermittedException nanpe){
				System.out.println("Null arguments not permittes");//TODO exit?
			}
//		}
//		else {
//			runGame(controller, model);
//		}
		
		System.exit(0);
		
		
	}
}
