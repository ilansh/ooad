package fourInARow.player;
import fourInARow.model.MyModel;
import fourInARow.model.PlayerNum;


public class Player implements IPlayer{
	
	PlayerStrategy _strategy;
	
	String _name;
	
	PlayerNum _playerNum;
	
	
	public Player(PlayerStrategy strategy, String name, PlayerNum playerNum) {
		if(strategy == null || name == null) {
			//TODO: throw exception
		}
		_strategy = strategy;
		_name = name;
		_playerNum = playerNum;
	}
	

	@Override
	public void setName(String name) {
		if(name == null) {
			//TODO: throw exception
		}
		_name = name;
		
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setStrategy(PlayerStrategy strategy) {
		if(strategy == null) {
			//TODO: throw exception
		}
		_strategy = strategy;
		
	}

	@Override
	public PlayerNum getPlayerNum() {
		return _playerNum;
	}

	@Override
	public void printWinMessage() {
		_strategy.printWinMessage(_playerNum.ordinal());
	}

	@Override
	public void printMoveMessage() {
		_strategy.printMoveMessage(_playerNum.ordinal());
		
	}

	@Override
	public int move(MyModel model) {
		if(model == null) {
			//TODO: Throw Exception
		}
		return _strategy.makeMove(model);
		
	}
}
