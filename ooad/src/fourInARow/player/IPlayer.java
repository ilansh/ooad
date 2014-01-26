package fourInARow.player;
import fourInARow.excpetion.NullArgumentNotPermittedException;
import fourInARow.model.IModel;


public interface IPlayer {
	
	public void setName(String name)  throws NullArgumentNotPermittedException;
	
	public String getName();
	
	public void setStrategy(PlayerStrategy strategy)  throws NullArgumentNotPermittedException;
	
	public int getPlayerNum();
	
	public void printWinMessage();
	
	public void printMoveMessage();

	public int move(IModel model) throws NullArgumentNotPermittedException;
	
}
