package fourInARow.unitTests;

import static org.junit.Assert.*;

import org.junit.*;

import fourInARow.defaultImplementation.HumanStrategy;
import fourInARow.excpetion.NullArgumentNotPermittedException;
import fourInARow.player.Player;
import fourInARow.player.PlayerStrategy;

public class PlayerTest {

	private PlayerStrategy _st;
	private Player _pl;
	
	@Before
	public void setUp(){
		_st = new HumanStrategy();
		try {
			_pl = new Player(_st, "Ehud", 1);
		}
		catch(Exception e) {
			
		}
	}
	
	@After
	public void tearDown(){
		//run after each test
		
	}
	
	//TODO this how we test exceptions: @Test(expected = NumberFormatException.class)
	
//	@Test (expected = NullArgumentNotPermittedException.class)
	
	//test getters
	@Test
	public void testGetName() throws NullArgumentNotPermittedException {
		Player player = new Player(_st, "Ehud", 1);
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
	}
	
	@Test
	public void testGetPlayerNum() {
//		Player player = new Player(null, "Ehud", 1);
//		assertEquals("Player's number should be 1", 1, player.getPlayerNum());
	}
	
	@Test
	public void testSetNameValidName() throws NullArgumentNotPermittedException {
		Player player = new Player(null, "Ehud", 1);
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
		player.setName("Ilan");
		assertEquals("Player's name should be Ilan", "Ilan", player.getName());
	}
	
	@Test
	public void testSetNameNullName() throws NullArgumentNotPermittedException {
		Player player = new Player(null, "Ehud", 1);
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
		player.setName(null);
		assertEquals("Player's name should be Ilan", "Ilan", player.getName());
	}

}
