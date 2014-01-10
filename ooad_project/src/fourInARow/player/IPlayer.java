package fourInARow.player;
import fourInARow.model.FourInARowModel;


public interface IPlayer {
	
	public void setName(String name);
	
	public String getName();
	
	public void setStrategy(PlayerStrategy strategy);
	
	public void setPlayerNum(int num);
	
	public int getPlayerNum();
	
	public void printWinMessage();
	
	public void printMoveMessage();

	public int move(FourInARowModel model);
	
}
