package fourInARow.player;

import fourInARow.excpetion.NullArgumentNotPermittedException;
import fourInARow.model.IModel;

public class Player implements IPlayer {

	protected PlayerStrategy _strategy;

	protected String _name;

	protected int _playerNum;

	public Player(PlayerStrategy strategy, String name, int playerNum)
			throws NullArgumentNotPermittedException {
		_playerNum = playerNum;
		setStrategy(strategy);
		setName(name);
	}

	@Override
	public void setName(String name) throws NullArgumentNotPermittedException {
		if (name == null) {
			throw new NullArgumentNotPermittedException();
		}
		_name = name;

	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setStrategy(PlayerStrategy strategy)
			throws NullArgumentNotPermittedException {
		if (strategy == null) {
			throw new NullArgumentNotPermittedException();
		}
		_strategy = strategy;

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
	public int move(IModel model) throws NullArgumentNotPermittedException {
		if (model == null) {
			throw new NullArgumentNotPermittedException();
		}
		return _strategy.makeMove(model);

	}
}
