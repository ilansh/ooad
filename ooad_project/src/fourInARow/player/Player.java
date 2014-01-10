package fourInARow.player;
import fourInARow.model.FourInARowModel;


public class Player implements IPlayer{
	
	PlayerStrategy _strategy;
	
	String _name;
	
	int _playerNum;
	
	
	public Player(PlayerStrategy strategy, String name, int playerNum) {
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
	public void setPlayerNum(int num) {
		_playerNum = num;
		
	}

	@Override
	public int getPlayerNum() {
		return _playerNum;
	}

	@Override
	public void printWinMessage() {
		_strategy.printWinMessage(_playerNum);
	}

	@Override
	public void printMoveMessage() {
		_strategy.printMoveMessage(_playerNum);
		
	}

	@Override
	public int move(FourInARowModel model) {
		if(model == null) {
			//TODO: Throw Exception
		}
		return _strategy.makeMove(model);
		
	}
}
