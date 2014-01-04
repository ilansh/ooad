package fourInARow.controller;

import fourInARow.model.FourInARowModel;

public interface PlayerStrategy {

	public int makeMove(FourInARowModel model);
	
	public void printWinMessage(int playerNum);
	
}
