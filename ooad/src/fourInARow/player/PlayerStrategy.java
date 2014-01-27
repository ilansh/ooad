package fourInARow.player;

import fourInARow.model.IModel;

public interface PlayerStrategy {

	/**
	 * Choose the next move based on the given model.
	 * @param model
	 * @return The chosen column to play next
	 */
	public int makeMove(IModel model);
	
	/**
	 * Print the win message for this strategy.
	 * @param playerNum
	 */
	public void printWinMessage(int playerNum);
	
	/**
	 * Print the message corresponding to a move by this strategy.
	 * @param playerNum
	 */
	public void printMoveMessage(int playerNum);
	
}
