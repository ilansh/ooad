package fourInARow.unitTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

import fourInARow.defaultImplementation.HumanStrategy;
import fourInARow.defaultImplementation.SimpleComputerStrategy;
import fourInARow.excpetion.NullArgumentNotPermittedException;
import fourInARow.model.MyModel;
import fourInARow.player.Player;
import fourInARow.player.PlayerStrategy;

public class PlayerTest {

	private PlayerStrategy _st;
	private Player _pl;
	private MyModel _model;
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	
	public class PlayerTester extends Player{

		public PlayerTester(PlayerStrategy strategy, String name, int playerNum)
				throws NullArgumentNotPermittedException {
			super(strategy, name, playerNum);
			// TODO Auto-generated constructor stub
		}
		
		PlayerStrategy getStrategey() {
			return _strategy;
		}
		
	}
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Before
	public void setUp() {
		_st = new SimpleComputerStrategy();
		_model = new MyModel(5, 6);
		setUpStreams();
		try {
			_pl = new Player(_st, "Ehud", 1);
		} catch (NullArgumentNotPermittedException e) {
			fail("Can't instanciate player");
		}

	}
	
	@After
	public void tearDown(){
		cleanUpStreams();
		
	}
	
	
	//test getters
	@Test
	public void testGetName() throws NullArgumentNotPermittedException {
		Player player = _pl;
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
	}
	
	@Test
	public void testGetPlayerNum() {
		Player player = _pl;
		assertEquals("Player's number should be 1", 1, player.getPlayerNum());
	}
	
	//test setters
	@Test
	public void testSetNameValidName() throws NullArgumentNotPermittedException {
		Player player = _pl;
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
		player.setName("Ilan");
		assertEquals("Player's name should be Ilan", "Ilan", player.getName());
	}
	
	@Test (expected = NullArgumentNotPermittedException.class)
	public void testSetNameNullName() throws NullArgumentNotPermittedException {
		Player player = _pl;
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
		player.setName(null);
	}
	
	@Test 
	public void testSetStrategyValid() throws NullArgumentNotPermittedException {
		PlayerTester player = new PlayerTester(new SimpleComputerStrategy(), "hi", 1);
		PlayerStrategy s = new HumanStrategy();
		player.setStrategy(s);
		assertEquals("strategies should be equal", s, player.getStrategey());
	}
	
	@Test (expected = NullArgumentNotPermittedException.class)
	public void testSetStrategyNull() throws NullArgumentNotPermittedException {
		Player player = _pl;
		player.setStrategy(null);
	}
	
	//test player move function
	//validates if the returned column from player's move function is in the model columns range.
	@Test
	public void testPlayerMoveValidModel() throws NullArgumentNotPermittedException {
		Player player = _pl;
		MyModel model = _model;
		int col = player.move(model);
		assertTrue("Column must be in range 0 - " + (model.getNumCols()-1) , 0 <= col && col < model.getNumCols());
	}
	
	@Test (expected = NullArgumentNotPermittedException.class)
	public void testPlayerMoveNullModel() throws NullArgumentNotPermittedException {
		Player player = _pl;
		player.move(null);
	}
	
	@Test
	public void testPlayerPrintWinMessage() throws NullArgumentNotPermittedException {
		Player player = _pl;
		player.setStrategy(new HumanStrategy());
		player.printWinMessage();
		
		assertEquals("Messages should be identical", "Game has ended! Player 1 won!" ,outContent.toString().trim());
	}
	
	@Test 
	public void testPlayerPrintMoveMessage() throws NullArgumentNotPermittedException {
		Player player = _pl;
		player.setStrategy(new HumanStrategy());
		player.printMoveMessage();
		
		assertEquals("Messages should be identical", "Player 1, choose a column:" ,outContent.toString().trim());
	}

}
