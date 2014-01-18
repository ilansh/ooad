package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameLibrary.Board;
import GameLibrary.Player;


/**
 * Class containing unit tests for the Player abstract class.
 * @author Ehud Doron (udid), Ortal Azoulay (ortalit)
 *
 */
public class PlayerTest {

	public class ThePlayer extends Player{
		@Override
		public int makeMove(Board board) {
			return 0;
		}
	}

	@Test
	public void getPlayerIDTest() {
		ThePlayer p1 = new ThePlayer();
		assertEquals("ID should be (byte)0", p1.getPlayerID(), (byte)0);
	}

	@Test
	public void setPlayerIDTest() {
		ThePlayer p1 = new ThePlayer();
		p1.setPlayerID((byte)1);
		assertEquals("ID should be equal", (byte)1, p1.getPlayerID());
		assertFalse("ID should be equal", (byte)0 == p1.getPlayerID());
	}


}

/**
 * 	public byte getPlayerID() {
		return _playerID;
	}


	public void setPlayerID(byte id) {
		_playerID = id;
	}
 */

