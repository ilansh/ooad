package fourInARow.player;
import fourInARow.excpetion.NullArgumentNotPermittedException;
import fourInARow.model.IModel;
import fourInARow.model.MyModel;


public interface IPlayer {
	
	public void setName(String name)  throws NullArgumentNotPermittedException;
	
	public String getName();
	
	public void setStrategy(PlayerStrategy strategy)  throws NullArgumentNotPermittedException;
	
	public int getPlayerNum();
	
	public void printWinMessage();
	
	public void printMoveMessage();

	public int move(IModel model) throws NullArgumentNotPermittedException;
	
}
