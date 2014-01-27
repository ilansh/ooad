package fourInARow.aspects;

import fourInARow.defaultImplementation.Driver;
import java.io.PrintWriter;
import java.lang.NumberFormatException;
import fourInARow.model.*;
import fourInARow.controller.*;

public aspect GameLogger {

	private static PrintWriter _logStream = null;

	private long _turnStartTime;
	
	private static int _curPlayer = 1;

	public static void initLogStream(PrintWriter logStream) {
		_logStream = logStream;
	}

	// point cuts
	pointcut startGame(): call(void initGame());

	pointcut startTurn(): call(GameStatus playTurn()) && within(Driver);

	pointcut makeMove(int col, int playerNum): call(GameStatus addDisc(int, int)) && args(col, playerNum) && within(AController);

	pointcut drawTurnBoard(Object board): call(void notifyObservers(Object)) && args(board) && within(MyModel);

	after(): startGame(){
		_logStream.println("Welcome to Four in a Line!");
	}

	before(): startTurn(){
		if (_turnStartTime == 0) { // reset only after valid turn
			_turnStartTime = System.currentTimeMillis();
		}
	}

	after(int col, int playerNum) returning(GameStatus gameStatus): makeMove(col, playerNum) {
		long timeDiff = System.currentTimeMillis() - _turnStartTime;
		long minutes = (timeDiff / (1000 * 60)) % 60;
		long seconds = (timeDiff / (1000)) % 60;
		long millis = timeDiff % 1000;
		_logStream.println("Player " + playerNum + " put a disc in column "
				+ col + ". Turn duration: " + minutes + "m:" + seconds + "s:"
				+ millis + "ms");
		_logStream.println();

		if (gameStatus == GameStatus.DRAW) {
			_logStream.println("Game ended with a draw.");
		} else if (gameStatus == GameStatus.WIN) {
			_logStream.println("Player " + playerNum + " has won the game!");
		}
		_logStream.println("================================================");
		_curPlayer ^= 3; //switch turn with xor
		_turnStartTime = 0;
	}

	after(int col, int playerNum) throwing: makeMove(col, playerNum) {
		_logStream.println("Player " + playerNum + " made an illegal choice at column "
				+ col);
	}

	after() throwing (NumberFormatException nfe): startTurn() {
		_logStream
				.println("Player " + _curPlayer + " made a non-numerical column choice.");
	}

	after(Object board): drawTurnBoard(board){
		int[][] b = (int[][]) board;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append('|');
			for (int j = 0; j < b[0].length; j++) {
				if (b[i][j] == 0) {
					sb.append(' ');
				} else if (b[i][j] == 1) {
					sb.append('x');
				} else {
					sb.append('o');
				}
				sb.append('|');
			}
			sb.append('\n');
		}
		for (int i = 0; i < b[0].length; i++) {
			sb.append(" -");
		}
		_logStream.println(sb.toString());
	}

}
