package UnitTests;

import Exceptions.IllegalValueException;
import GameLibrary.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Class containing unit tests for the Game object.
 * @author Ehud Doron (udid), Ortal Azoulay (ortalit)
 *
 */
public class GameTest {
	
	
	/*****************************
	 * Replacement dependencies. *
	 *****************************/
	
	
	public static class DiskDependency implements Disk {

		@Override
		public void show(int row, int col) {
			System.out.print("Here we would print the disk");
		}
		
	}
	
	
	
	public static class BoardDisplayDependency implements BoardDisplay {

		@Override
		public void show(Board board, Disk player1Disk, Disk player2Disk) {
			System.out.println("Here we would print the board.");
			
		}
		
	}
	
	
	
	public static class GameDisplayDependency implements GameDisplay {
		public GameDisplayDependency() {
		}

		@Override
		public void show(BoardDisplay boardDisplay, Board brd,
				Disk player1Disk, Disk player2Disk) {
			boardDisplay.show(brd, player1Disk, player2Disk);
		}

		@Override
		public void showMessage(Message message) {
			System.out.println(message.getText());
		}
	}
	
	
	public static class PlayerDependency extends Player {
		public PlayerDependency() {
			super();
		}

		@Override
		public int makeMove(Board board) {
			return 0;
		}
	}
	
	public static class PlayerDependency2 extends Player {
		public PlayerDependency2() {
			super();
		}

		@Override
		public int makeMove(Board board) {
			return 1;
		}
	}
	
	
	public static class CompositeDisplayDependency extends CompositeDisplay {
		
		public CompositeDisplayDependency(GameDisplay gd, BoardDisplay bd, 
							Disk p1, Disk p2) {
			super(gd, bd, p1, p2);
		}
		
	}
	
	
	private Board brd, brd_2x2;
	private BoardDisplay bd;
	private GameDisplay gd;
	private CompositeDisplay cd;
	private Player p1, p2;
	private Disk d1, d2;
	
	@Before
	public void initialize() throws IllegalValueException {
		brd_2x2 = new Board(2, 2, 2);
		brd = new Board();
		bd = new BoardDisplayDependency();
		gd = new GameDisplayDependency();
		d1 = new DiskDependency();
		d2 = new DiskDependency();
		p1 = new PlayerDependency();
		p2 = new PlayerDependency2();
		cd = new CompositeDisplayDependency(gd, bd, d1, d2);
	}
	
	
	/**********************
	 *  Constructor tests *
	 **********************/
	
	
	@Test
	public void NoBoardConstructor_RegularValues_EmptyBoard() {
		Game g;
		try {
			g = new Game(p1, p2, cd);
			assertEquals("Start-of-game board should be empty", true, 
					(g.getBoard().getValue(0, 0) == (byte) 0));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the game constructor");
		}
	}
	
	
	@Test
	public void WithBoardConstructor_RegularValues_EmptyBoard() {
		Game g;
		try {
			g = new Game(p1, p2, brd, cd);
			assertEquals("Start-of-game board should be empty", true, 
					(g.getBoard().getValue(0, 0) == (byte) 0));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the game constructor");
		}
		
	}
	
	
	@Test (expected = IllegalValueException.class)
	public void WithBoardConstructor_NullValues() throws IllegalValueException {
		Game g = null;
		g = new Game(null, null, null, null);
		g.resetGame(); //to get rid of the warning; shouldn't execute.
	}
	
	@Test (expected = IllegalValueException.class)
	public void noBoardConstructor_NullValues() throws IllegalValueException {
		Game g = null;
		g = new Game(null, null, null);
		g.resetGame(); //to get rid of the warning; shouldn't execute.
	}
	
	
	
	/***************************
	 * Setters & Getters tests *
	 ***************************/
	
	
	 public class newPlayer extends Player{
	        @Override
	        public int makeMove(Board board) {
	            return 3;
	        }
	    }
	   
	    @Test
	    public void getBoardTest() {
	        Game game;
			try {
				game = new Game(p1, p2, brd, cd);
		        assertEquals("Board should be the same", game.getBoard(), brd);
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	   
	    @Test
	    public void getCompositeDisplayTest() {
	        Game game;
			try {
				game = new Game(p1, p2, cd);
		        assertEquals("CompositeDisplay should be the same", game.getCompositeDisplay(), cd);
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	    
	    
	    @Test
	    public void removeCompositeDisplay_onlyCompositeDisplayOrNull_False() {
	    	Game game;
	    	try {
				game = new Game(p1, p2, cd);
				//trying to remove the game's only composite display
				boolean res = game.removeCompositeDisplay(cd);
				assertEquals("Should not be able to remove only composite display", false, res);
				//trying to remove null
				res = game.removeCompositeDisplay(null);
				assertEquals("Should not be able to remove 'null' from the observer list", false, res);
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	    
	    
	    @Test
	    public void removeCompositeDisplay_otherCompositeDisplay_True() {
	    	Game game;
	    	try {
				game = new Game(p1, p2, cd);
				//creating a new CD and adding it to the game
				CompositeDisplay cd2 = new CompositeDisplay(gd, bd, d1, d2);
				game.addCompositeDisplay(cd2);
				//removing it
				boolean res = game.removeCompositeDisplay(cd2);
				assertEquals("Should be able to remove second composite display", true, res);
				assertEquals("Number of game CD-s should be 1", 1, game.getBoard().countObservers());
				//adding it, then removing the original CD
				game.addCompositeDisplay(cd2);
				assertEquals("Number of game CD-s should be 2", 2, game.getBoard().countObservers());
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	    
	    
	    @Test
	    public void setOnlyCompositeDisplay_regularCompositeDisplay_OneCD() {
	    	Game game;
	    	try {
				game = new Game(p1, p2, cd);
				//creating a new CD and adding it to the game
				CompositeDisplay cd2 = new CompositeDisplay(gd, bd, d1, d2);
				game.addCompositeDisplay(cd2);
				//setting it to be the game's only CD
				game.setOnlyCompositeDisplay(cd2);
				assertEquals("Game's CD should be cd2", cd2, game.getCompositeDisplay());
				assertEquals("Number of board observers should be 1", 1, game.getBoard().countObservers());
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	    
	    
		@Test(expected = IllegalValueException.class)
	    public void setOnlyCompositeDisplay_BadInput_IllegalValueException() throws IllegalValueException {
	    	Game game = new Game(p1, p2, cd);
	    	game.setOnlyCompositeDisplay(null);
	    }
	   
	    @Test
	    public void getPlayer1Test() {
	        Game game;
			try {
				game = new Game(p1, p2, cd);
				assertEquals("Player1 should be the same", game.getPlayer1(), p1);
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	   
	    @Test
	    public void getPlayer2Test() {
	        Game game;
			try {
				game = new Game(p1, p2, cd);
		        assertEquals("Player2 should be the same", game.getPlayer2(), p2);
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	   
	    @Test
	    public void setBoardTest() {
	        Game game;
			try {
				game = new Game(p1, p2, cd);
		        Board newBrd = new Board();
		        game.setBoard(newBrd);
		       
		        assertEquals("New Board should be the same", game.getBoard(), newBrd);
		        assertFalse("New Board should not be equal to the previous one", game.getBoard().equals(brd));
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	   
	    @Test
	    public void setPlayer1Test() {
	        Game game;
			try {
				game = new Game(p1, p2, cd);
		        newPlayer newP1 = new newPlayer();
		        game.setPlayer1(newP1);
		       
		        assertEquals("New Player 1 should be the same", game.getPlayer1(), newP1);
		        assertFalse("New Player 1 should not be equal to the previous one", game.getPlayer1().equals(p1));
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	   
	    @Test
	    public void setPlayer2Test() {
	        Game game;
			try {
				game = new Game(p1, p2, cd);
		        newPlayer newP2 = new newPlayer();
		        game.setPlayer2(newP2);
		       
		        assertEquals("New Player 2 should be the same", game.getPlayer2(), newP2);
		        assertFalse("New Player 2 should not be equal to the previous one", game.getPlayer2().equals(p2));
			} catch (IllegalValueException e) {
				fail("An IllegalValueException was thrown by the game constructor");
			}
	    }
	
	
	
	
	/***************
	 * Other tests *
	 ***************/
	
	
	@Test
	public void ResetGame_CurrentPlayerIsOne() {
		Game g;
		try {
			g = new Game(p1, p2, cd);
			g.resetGame();
			assertEquals("After resetting, current player should be 1", 
						Board.PLAYER_1, g.getCurrentPlayer());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the game constructor");
		}
	}
	
	
	@Test
	public void ChangeTurnTest() {
		Game g;
		try {
			g = new Game(p1, p2, cd);
			assertEquals("After initializing game, current player should be 1", 
					Board.PLAYER_1, g.getCurrentPlayer());
			g.changeTurn();
			assertEquals("Changing from player 1 - current player should be 2", 
					Board.PLAYER_2, g.getCurrentPlayer());
			g.changeTurn();
			assertEquals("Changing from player 2 - current player should be 1",
					Board.PLAYER_1, g.getCurrentPlayer());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the game constructor");
		}
	}
	
	
	@Test
	public void PlayTurn_RegularMove_DiskAtColumnZero() {
		Game g;
		try {
			g = new Game(p1, p2, cd);
			g.playTurn();
			assertEquals("After making a move, the disk at (5, 0) should be player 1's",
						Board.PLAYER_1,
						g.getBoard().getValue(g.getBoard().getBoardHeight()-1, 0));
			assertEquals("The current player should be player 2", 
						Board.PLAYER_2, g.getCurrentPlayer());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the game constructor");
		}
	}
	
	
	@Test
	public void addCompositeDisplay_CompositeDisplayAdded() {
		Game g;
		try {
			g = new Game(p1, p2, cd);
			g.addCompositeDisplay(new CompositeDisplayDependency(gd, bd, d1, d1));
			assertEquals("The number of observers of the game's board should be 2",
						2, g.getBoard().countObservers());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the game constructor");
		}
	}
	
	@Test (expected = IllegalValueException.class)
	public void addCompositeDisplay_Null() throws IllegalValueException {
		Game g;
		g = new Game(p1, p2, cd);
		g.addCompositeDisplay(null);
	}

	@Ignore("Template for tests")
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test 
	public void checkWinnerTest_WhenThereIsAWinner() throws IllegalValueException {
		Board b = new Board(3, 3, 2);
		//filling the board
		Game g;
		g = new Game(p1, p2, b, cd);
		b.setLastPlayer((byte) 1);
		b.insertInto(0);
		g.addCompositeDisplay(new CompositeDisplayDependency(gd, bd, d1, d1));
		g.playTurn();
		assertEquals("Player 1 should be the winner", (byte)1, g.getBoard().getWinner());		
	}
	
	
	@Test
	public void showGameTest() throws IllegalValueException {
		Game g;
		g = new Game(p1, p2, brd_2x2, cd);
		g.addCompositeDisplay(new CompositeDisplayDependency(gd, bd, d1, d1));
		g.showGame();
	}
	
	
	
	

}
