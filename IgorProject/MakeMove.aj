/*
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import FourInARow.model.GameState;
import FourInARow.view.CellGraphics;


public aspect MakeMove {

	PrintWriter writer;
	int turn;

	public MakeMove(){
		try {writer = new PrintWriter("AspectJLog.txt", "UTF-8"); } 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (UnsupportedEncodingException e) { e.printStackTrace(); }
	}

	//Recored when move made and who made the move
	pointcut whosTurn():
		call (public int FourInARow.model.FourInARowModel.getTurn(..));

	after() returning (Object o): whosTurn() {
		turn = (int) o;
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		writer.println("time is:"+sdf.format(cal.getTime())+". Player number "+turn+" made a move.");//TODO:change time to look nicer 
	}

	//Recored the board when is changes
	pointcut drewGFX(int[][] board):
		call (public Object FourInARow.view.Graphics.drawGraphics(int[][]))
		&& args(board);
	//	before(int[][] board): drewGFX(board) {
	//	//	System.out.println("board is "+board.toString());
	//	}
	after() returning (Object o): drewGFX(int[][]) {
		char returnvalue;
		if (o != null)
		{
			returnvalue = (char) o;
			if (o.equals('\n'))
				writer.println("");
			else
				writer.print(o);
			
		}
	}

	pointcut gameState():
		call(public FourInARow.model.GameState FourInARow.model.ModelInterface.getGameState());

	after() returning (Object o): gameState(){
		GameState returnvalue = (GameState)o;
		if (returnvalue == GameState.WON){
			writer.println("Player "+turn+" Won the game");
			writer.close();
		}
		else if (returnvalue == GameState.DRAW){
			writer.println("Game ends at a draw");
			writer.close();
		}
	}
}
*/