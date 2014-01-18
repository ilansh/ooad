package fourInARow.unitTests;

import static org.junit.Assert.*;

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
	
	@Before
	public void setUp() throws NullArgumentNotPermittedException{
		_st = new SimpleComputerStrategy();
		_pl = new Player(_st, "Ehud", 1);
		_model = new MyModel(5, 6);

	}
	
	@After
	public void tearDown(){
		//run after each test
		
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
		assertEquals("Player's name should be Ilan", "Ilan", player.getName());
	}
	
	//test player move function
	//validates if the returned column from player's move function is in the model columns range.
	@Test
	public void testPlayerMoveValidModel() throws NullArgumentNotPermittedException {
		Player player = _pl;
		MyModel model = _model;
		int col = player.move(model);
		assertTrue("Column must be in range 0 - " + (model.getNumCols()-1) , 0 <= col && col < model.getNumCols());
		//TODO maybe it is better to only check here if the returend column is integer and in the addDisc method check the range
	}
	
	@Test (expected = NullArgumentNotPermittedException.class)
	public void testPlayerMoveNullModel() throws NullArgumentNotPermittedException {
		Player player = _pl;
		player.move(null);
	}

}