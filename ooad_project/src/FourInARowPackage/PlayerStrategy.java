package FourInARowPackage;

public interface PlayerStrategy {

	public int makeMove(FourInARowModel model);
	
	public void printWinMessage(int playerNum);
	
}
