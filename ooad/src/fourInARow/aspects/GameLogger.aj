package fourInARow.aspects;

import java.io.PrintWriter;
import java.util.Observable;

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
	
    after(int col, int playerNum) returning(GameStatus gameStatus): call(GameStatus addDisc(int, int)) 
    											&& args(col, playerNum) {
    	long timeDiff = System.currentTimeMillis() - _turnStartTime;
    	long minutes = (timeDiff / (1000 * 60)) % 60;
    	long seconds = (timeDiff / (1000)) % 60;
    	long millis = timeDiff % 1000;
    	_logStream.println("Player " + playerNum + " put a disc in column " + col + ". Turn duration: " + 
    						minutes + "m:" + seconds +"s:" + millis + "ms");
    	if(gameStatus == GameStatus.DRAW) {
    		_logStream.println("Game ended with a draw.");
    	}
    	else if(gameStatus == GameStatus.WIN) {
    		_logStream.println("Player " + playerNum + " has won the game!");
    	}
    }
    
    after(int col, int playerNum) throwing: call(GameStatus addDisc(int, int)) 
												&& args(col, playerNum) {
    	_logStream.println("Player " + playerNum + " invalid choice at column " + col + ", restarting turn timer.");
	}
    
    after() throwing: call(GameStatus playTurn()) {
    	_logStream.println("invalid column choice, a numerical entry is required");
    }
    
    
    //pointcut drewBoard(Observable model, Object board):  call(void update(Observable, Object)) && args(model, board);
    
    after(Observable model, Object board): call(void update(Observable, Object)) && args(model, board){  //add within
    	_logStream.println(model.toString());
    }
    
    after(Object board): call(void notifyObservers(Object)) && args(board){  //add within
    	int[][] b = (int[][])board;
    	
    	StringBuffer sb = new StringBuffer();
    	_logStream.println();
		for(int i = 0; i < b.length; i ++) {
			sb.append('|');
			for(int j = 0; j < b[0].length; j++) {
				if(b[i][j] == 0) {
					sb.append(' ');
				}
				else if (b[i][j] == 1) {
					sb.append('x');
				}
				else {
					sb.append('o');
				}
				sb.append('|');
			}
			sb.append('\n');
		}
		for(int i = 0; i < b[0].length ; i++) {
			sb.append(" -");
		}
		_logStream.println(sb.toString());
    }

}
