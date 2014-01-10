package fourInARow.player;

import fourInARow.model.FourInARowModel;

public interface PlayerStrategy {

	public int makeMove(FourInARowModel model);
	
	public void printWinMessage(int playerNum);
	
	public void printMoveMessage(int playerNum);
	
}
