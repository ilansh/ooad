package fourInARow.player;

import fourInARow.model.MyModel;

public interface PlayerStrategy {

	public int makeMove(MyModel model);
	
	public void printWinMessage(int playerNum);
	
	public void printMoveMessage(int playerNum);
	
}
