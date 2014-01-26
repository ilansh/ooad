package fourInARow.player;

import fourInARow.model.IModel;

public interface PlayerStrategy {

	public int makeMove(IModel model);
	
	public void printWinMessage(int playerNum);
	
	public void printMoveMessage(int playerNum);
	
}
