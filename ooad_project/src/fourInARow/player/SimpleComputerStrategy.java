package fourInARow.player;

import fourInARow.model.MyModel;

public class SimpleComputerStrategy implements PlayerStrategy
{

	public static final int EMPTY_CELL = 0;
	public static final int PLAYER1 = 1;
	public static final int PLAYER2 = 2;
	
	
	@Override
	public int makeMove(MyModel model) {
		int emptyrow= 0;
		int[][] board = model.getBoard();
		int cols = model.getNumCols();
		// first check if a move can win
		for (int i=0; i< cols; i++) {
			if (!isColumnFull(board, i)) {
				emptyrow = firstEmptyRow(board,i);
				board[emptyrow][i] = PLAYER2;
				if (model.isWinner(board, i, emptyrow, PLAYER2)) {
					board[emptyrow][i] = EMPTY_CELL; // reset
					return i;
				}
				board[emptyrow][i] = EMPTY_CELL; // reset
			}
		}
		// otherwise then pick up any move that will prevent other player to win 
		// in case there is a win on next turn
		int counter = 0; // i count other player possible winnings
		int chosenrow = 0; 
		for (int i=0; i< cols; i++) {
			if (!isColumnFull(board,i)) {
				emptyrow = firstEmptyRow(board,i);
				board[emptyrow][i] = PLAYER1; // assume the other player does this
				if (model.isWinner(board, i, emptyrow, PLAYER1)) {
					board[emptyrow][i] = EMPTY_CELL; // reset
					counter++; // we found a winning disc
					chosenrow = i; // remember the row
				}
				board[emptyrow][i] = EMPTY_CELL; // reset
			}
		}
		// we block the player if there is exactly one winning disc 
		if (counter==1) return chosenrow;
		
		// else if other player wins no matter what, pick up first non full column
		for (int i=0; i<cols; i++) 
			if (!isColumnFull(board,i)){ 
				return i;
			}
		return -1; 
	}
	
	private static boolean isColumnFull(int[][] board, int colIndex){
		for (int i = 0; i < board.length; i++) {
			if (board[i][colIndex] == EMPTY_CELL)
				return false;
		}
		return true;
	}
	
	
	// returns the ROW index of the first empty cell in the COLUMN rowIndex. -1 if all full
	private static int firstEmptyRow(int[][] board, int colIndex) {
		for (int i = board.length-1; i >=0; i--) {
			if (board[i][colIndex] == EMPTY_CELL) return i;
		}
		return -1;
	}
	
	public void printWinMessage(int playerNum){
		System.out.println("Game has ended! The computer won!");
	}

	@Override
	public void printMoveMessage(int playerNum) {
		System.out.println("Computer makes a move:");
		System.out.println();
	}

}
