package fourInARow.player;

import fourInARow.model.IModel;
import fourInARow.model.MyModel;

public interface PlayerStrategy {

	public int makeMove(IModel model);
	
	public void printWinMessage(int playerNum);
	
	public void printMoveMessage(int playerNum);
	
}
