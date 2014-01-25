package fourInARow.aspects;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Observable;

import fourInARow.model.*;
import fourInARow.view.*;

public aspect GameLogger { //TODO: optimize

	 private static PrintWriter _logStream = null;
	
	 private long _turnStartTime;
	 
	 
	 public static void initLogStream(PrintWriter logStream) {
		 _logStream = logStream;
	 }
	 
	 //point cuts
	 pointcut startGame(): call(void mainMenu());
	 pointcut startTurn(): call(GameStatus playTurn());
	 pointcut makeMove(int col, int playerNum): call(GameStatus addDisc(int, int)) && args(col, playerNum);
	 //TODO within doesnt work because aspect couldnt know when it'll be called
	 pointcut drawBoard(Observable model, Object board):  call(void update(Observable, Object)) && args(model, board);// && within(View);
	 pointcut drawTurnBoard(Object board): call(void notifyObservers(Object)) && args(board) && within(MyModel);
	 
	 before(): startGame(){
		 _logStream.println("Welcome to Four in a Line!");
	 }
	
	 before(): startTurn(){
		 _turnStartTime = System.currentTimeMillis();
	 }
	 
    after(int col, int playerNum) returning(GameStatus gameStatus): makeMove(col, playerNum) {
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
    
    after(int col, int playerNum) throwing: makeMove(col, playerNum) {
    	_logStream.println("Player " + playerNum + " invalid choice at column " + col + ", restarting turn timer.");
	}
    
    after() throwing: startTurn() {
    	_logStream.println("invalid column choice, a numerical entry is required, restarting turn timer.");
    }
    
    
    after(Observable model, Object board): drawBoard(model, board){  //add within
    	_logStream.println(model.toString());
    }
    
    after(Object board): drawTurnBoard(board){  //add within
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
