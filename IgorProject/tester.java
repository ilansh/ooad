
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import FourInARow.controller.*;
import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;
import FourInARow.view.BoardGraphics;
import FourInARow.view.BoardGraphicsPool;
import FourInARow.view.CellGraphics;
import FourInARow.view.CellGraphicsPool;
import FourInARow.view.CompositeGraphics;
import FourInARow.view.FourInARowView;
import FourInARow.view.Graphics;
import FourInARow.view.ViewInterface;


public abstract class tester {

	public static void main(String[] args) throws IOException {
		RunFourInARow();
	}
	
	
	private static void RunFourInARow() throws IOException {
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("MethodCallsLog.txt", true)));
		
		ModelInterface myModel = (ModelInterface)logMethodCalls.newInstance(new FourInARowModel(4,5,3), out);
		
		//initiate graphic composite.
		Graphics gameGraphics = (Graphics) logMethodCalls.newInstance(new CompositeGraphics(), out);
		CellGraphicsPool.initPool(); //use static factory to receive cell instances
		Graphics playerOne = (Graphics) logMethodCalls.newInstance(CellGraphics.getCellInstance('x'), out);
		Graphics playerTwo = (Graphics) logMethodCalls.newInstance(CellGraphics.getCellInstance('o'), out);
		Graphics empty = (Graphics) logMethodCalls.newInstance(CellGraphics.getCellInstance(' '), out);
		Graphics boarder = (Graphics) logMethodCalls.newInstance(CellGraphics.getCellInstance('|'), out);
		Graphics newline = (Graphics) logMethodCalls.newInstance(CellGraphics.getCellInstance('\n'), out);
		BoardGraphicsPool.initPool();
		Graphics board = (Graphics) logMethodCalls.newInstance(BoardGraphics.getBoardInstance(playerOne, playerTwo, empty, boarder, newline), out);
		
		//igor
		/*gameGraphics.addGraphics(playerOne);
		gameGraphics.addGraphics(playerTwo);
		gameGraphics.addGraphics(empty);*/
		gameGraphics.addGraphics(board);
		
		//initiate views list and add a specific (textual) view.
		ArrayList<ViewInterface> views = new ArrayList<ViewInterface>();
		//FourInARowView view = new FourInARowView(gameGraphics);
		ViewInterface view = (ViewInterface)logMethodCalls.newInstance(new FourInARowView(gameGraphics), out);
		views.add(view);
		
		//tell Model about the Views. 
		for (ViewInterface v : views){
			myModel.addLocalObserver(v);
		}

		//initialize controller and connecting to it the model and the views
		
		ControllerInterface controller = (ControllerInterface) logMethodCalls.newInstance(new MyFourInARowController(myModel, views), out);
		controller.addlocalObserver(myModel);
		controller.startGame(null, null);
		out.close();
	} 


}
