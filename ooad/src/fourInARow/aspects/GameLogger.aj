package fourInARow.aspects;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import fourInARow.controller.*;
import fourInARow.model.*;

public aspect GameLogger { //TODO: optimize

	 private PrintWriter _logStream = null;
	
	 private long _turnStartTime;
	 
	 public  void initLogger(PrintWriter logStream) {
		 _logStream = logStream;
	 }

	
	 before(): call(GameStatus playTurn()){
		 _turnStartTime = System.currentTimeMillis();
	 }
	
		
	//measures time until valid choice, not including wrong choices
    after(int col, int playerNum) returning: call(GameStatus addDisc(int, int)) 
    											&& args(col, playerNum) {
    	long timeDiff = System.currentTimeMillis() - _turnStartTime;
    	long minutes = (timeDiff / (1000 * 60)) % 60;
    	long seconds = (timeDiff / (1000)) % 60;
    	long millis = timeDiff % 1000;
//    	long millis = (timeDiff) % 60;
    	_logStream.println("Player " + playerNum + " put a disc in column " + col + ". Turn duration: " + 
    						minutes + "m:" + seconds +"s:" + millis + "ms");
    }
    
//    after(int col, int playerNum) throwing: call(GameStatus addDisc(int, int)) 
//												&& args(col, playerNum) {
//		//print error message
//	}
//    
//    after(String str): call(void java.io.PrintStream.println(..)) && args(str) {
//		_logStream.println(str);
//    }
//    
//    after(): call(void java.io.PrintStream.println()) {
//		_logStream.println();
//    }
//    
//    after(String str): call(void java.io.PrintStream.print(..)) && args(str) {
//		_logStream.print(str);
//    }
//    
//    after(char c): call(void java.io.PrintStream.print(..)) && args(c) {
//		_logStream.print(c);
//    }
}
