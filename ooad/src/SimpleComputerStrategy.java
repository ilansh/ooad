

import fourInARow.model.MyModel;
import fourInARow.model.PlayerNum;
import fourInARow.player.PlayerStrategy;

public class SimpleComputerStrategy implements PlayerStrategy
{
	
	
	@Override
	public int makeMove(MyModel model) {
		int emptyrow= 0;
		PlayerNum[][] board = model.getBoard();
		int cols = model.getNumCols();
		// first check if a move can win
		for (int i=0; i< cols; i++) {
			if (!isColumnFull(board, i)) {
				emptyrow = firstEmptyRow(board,i);
				board[emptyrow][i] = PlayerNum.PLAYER2;
				if (model.isWinner(board, i, emptyrow, PlayerNum.PLAYER2)) {
					board[emptyrow][i] = PlayerNum.EMPTY; // reset
					return i;
				}
				board[emptyrow][i] = PlayerNum.EMPTY; // reset
			}
		}
		// otherwise then pick up any move that will prevent other player to win 
		// in case there is a win on next turn
		int counter = 0; // i count other player possible winnings
		int chosenrow = 0; 
		for (int i=0; i< cols; i++) {
			if (!isColumnFull(board,i)) {
				emptyrow = firstEmptyRow(board,i);
				board[emptyrow][i] = PlayerNum.PLAYER1; // assume the other player does this
				if (model.isWinner(board, i, emptyrow, PlayerNum.PLAYER1)) {
					board[emptyrow][i] = PlayerNum.EMPTY; // reset
					counter++; // we found a winning disc
					chosenrow = i; // remember the row
				}
				board[emptyrow][i] = PlayerNum.EMPTY; // reset
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
	
	private static boolean isColumnFull(PlayerNum[][] board, int colIndex){
		for (int i = 0; i < board.length; i++) {
			if (board[i][colIndex] == PlayerNum.EMPTY)
				return false;
		}
		return true;
	}
	
	
	// returns the ROW index of the first empty cell in the COLUMN rowIndex. -1 if all full
	private static int firstEmptyRow(PlayerNum[][] board, int colIndex) {
		for (int i = board.length-1; i >=0; i--) {
			if (board[i][colIndex] == PlayerNum.EMPTY) return i;
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
