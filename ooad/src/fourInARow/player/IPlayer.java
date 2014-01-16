package fourInARow.player;
import fourInARow.model.MyModel;
import fourInARow.model.PlayerNum;


public interface IPlayer {
	
	public void setName(String name);
	
	public String getName();
	
	public void setStrategy(PlayerStrategy strategy);
	
	public PlayerNum getPlayerNum();
	
	public void printWinMessage();
	
	public void printMoveMessage();

	public int move(MyModel model);
	
}
