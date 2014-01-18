package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameLibrary.*;

/**
 * Class containing unit tests for the CompositeDisplay objects.
 * @author Ehud Doron (udid), Ortal Azoulay (ortalit)
 *
 */
public class CompositeDisplayTest {

///////////////////////////////////////////////////////////////////////////////////////////	
    public class Disk1 implements Disk {
		@Override
		public void show(int row, int col) {}
    }
    
    public class Disk2 implements Disk {
		@Override
		public void show(int row, int col) {}
    }
    
    public class BoardDisplay1 implements BoardDisplay {
		@Override
		public void show(Board board, Disk player1Disk, Disk player2Disk) {}
    }
    
    public class GameDisplay1 implements GameDisplay {

		@Override
		public void show(BoardDisplay boardDisplay, Board brd,
				Disk player1Disk, Disk player2Disk) {
			
		}

		@Override
		public void showMessage(Message message) {
			
		}
		
    } 
/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void createCompositeDisplayCheckGetGameDisplay() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
	
		assertEquals("GameDisplay should be the same", cd.getGameDisplay(), gd);
	}
	
	@Test
	public void createCompositeDisplayCheckGetBoardDisplay() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
	
		assertEquals("BoardDisplay should be the same", cd.getBoardDisplay(), bd);
	}
	
	@Test
	public void createCompositeDisplayCheckGetPlayer1Disk() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
	
		assertEquals("Player1Disk should be the same", cd.getPlayer1Disk(), p1);
	}
	
	@Test
	public void createCompositeDisplayCheckGetPlayer2Disk() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
	
		assertEquals("Player2Disk should be the same", cd.getPlayer2Disk(), p2);
	}
	
	@Test
	public void createCompositeDisplayCheckSetGameDisplay() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
		GameDisplay gd2 = new GameDisplay1();
		cd.setGameDisplay(gd2);
	
		assertEquals("GameDisplay should be the same", cd.getGameDisplay(), gd2);
		assertFalse("GameDisplay should NOT be the same as the one previous to the set", cd.getGameDisplay().equals(gd));
	}
	
	@Test
	public void createCompositeDisplayCheckSetBoardDisplay() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
		BoardDisplay bd2 = new BoardDisplay1();
		cd.setBoardDisplay(bd2);
	
		assertEquals("BoardDisplay should be the same", cd.getBoardDisplay(), bd2);
		assertFalse("BoardDisplay should NOT be the same as the one previous to the set", cd.getBoardDisplay().equals(bd));
		
	}
	
	@Test
	public void createCompositeDisplayCheckSetPlayer1Disk() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
		Disk1 p1New = new Disk1();
		cd.setPlayer1Disk(p1New);
	
		assertEquals("Player1Disk should be the same", cd.getPlayer1Disk(), p1New);
		assertFalse("Player1Disk should NOT be the same as the one previous to the set", cd.getPlayer1Disk().equals(p1));
	}
	
	@Test
	public void createCompositeDisplayCheckSetPlayer2Disk() {
  
		Disk1 p1 = new Disk1();
		Disk2 p2 = new Disk2();
		BoardDisplay1 bd = new BoardDisplay1();
		GameDisplay gd = new GameDisplay1();
		CompositeDisplay cd = new CompositeDisplay(gd, bd, p1, p2);
		Disk2 p2New = new Disk2();
		cd.setPlayer2Disk(p2New);
	
		assertEquals("Player1Disk should be the same", cd.getPlayer2Disk(), p2New);
		assertFalse("Player1Disk should NOT be the same as the one previous to the set", cd.getPlayer2Disk().equals(p2));
	}
	
	/*
	 * 
	 * 	
	private GameDisplay _gameDisplay;
	private BoardDisplay _boardDisplay;
	private Disk _player1Disk, _player2Disk;
	

	public CompositeDisplay (GameDisplay gd, BoardDisplay bd, Disk p1, Disk p2){
		_gameDisplay = gd;
		_boardDisplay = bd;
		_player1Disk = p1;
		_player2Disk = p2;
	}

	///////////////////////////////////////////
	///////     Getters & Setters        //////
	///////////////////////////////////////////
	

	public GameDisplay getGameDisplay() {
		return _gameDisplay;
	}

	

	public void setGameDisplay(GameDisplay _gameDisplay) {
		this._gameDisplay = _gameDisplay;
	}


	public BoardDisplay getBoardDisplay() {
		return _boardDisplay;
	}

	
	
	public void setBoardDisplay(BoardDisplay _boardDisplay) {
		this._boardDisplay = _boardDisplay;
	}

	

	public Disk getPlayer1Disk() {
		return _player1Disk;
	}

	

	public void setPlayer1Disk(Disk _player1Disk) {
		this._player1Disk = _player1Disk;
	}

	

	public Disk getPlayer2Disk() {
		return _player2Disk;
	}


	public void setPlayer2Disk(Disk _player2Disk) {
		this._player2Disk = _player2Disk;
	}
	 */

}
