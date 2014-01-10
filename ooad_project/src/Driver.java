

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fourInARow.controller.*;
import fourInARow.view.*;
import fourInARow.model.*;
import fourInARow.loggingProxy.*;

public class Driver {
	public static void main(String[] args) {
		
		//TODO: Do you want to log method calls (do through run arguments)
		
		PrintWriter controllerLog = null;
		PrintWriter modelLog = null;
		try {
			controllerLog = new PrintWriter("contoller.log", "UTF-8");
			modelLog = new PrintWriter("model.log", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FourInARowModel model = new FourInARowModel(5, 6);
		
		
		GameController controller = new MyGame(model);
		GameController game = (GameController)LoggingProxy.newInstance(controller, controllerLog);
		
		game.mainMenu();
		game.initViews();
		
		FourInARowView view = new FourInARowView(new WindowGraphic());
	
		game.addView(view);
		
		GameStatus status = game.getGameStatus();
		
		do{
			status = game.playTurn();
		} while(status != GameStatus.WIN && status != GameStatus.DRAW);
	
		game.printEndMessage();
		
		controllerLog.close();
		modelLog.close();
	}
}
