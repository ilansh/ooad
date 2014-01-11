package fourInARow.aspects;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import fourInARow.model.*;

public aspect GameLogger { //TODO: optimize

	 private PrintWriter _logStream = null;
	
	public  void initLogger(PrintWriter logStream) {
		_logStream = logStream;
	}

	
    after(int col, int playerNum): call(GameStatus addDisc(int, int)) 
    								&& args(col, playerNum) {
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	Date date = new Date(System.currentTimeMillis());
    	_logStream.println("Player " + playerNum + " put a disc in column " + col + " at time: " + 
    						dateFormat.format(date));
    }
    
    after(String str): call(void java.io.PrintStream.println(..)) && args(str) {
		_logStream.println(str);
    }
    
    after(): call(void java.io.PrintStream.println()) {
		_logStream.println();
    }
    
    after(String str): call(void java.io.PrintStream.print(..)) && args(str) {
		_logStream.print(str);
    }
    
    after(char c): call(void java.io.PrintStream.print(..)) && args(c) {
		_logStream.print(c);
    }
}
