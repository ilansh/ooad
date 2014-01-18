package fourInARow.unitTests;

import static org.junit.Assert.*;

import org.junit.*;

import fourInARow.player.Player;
import fourInARow.player.PlayerStrategy;

public class PlayerTest {

	@Before
	public void setUp(){
		//run before each test
	}
	
	@After
	public void tearDown(){
		//run after each test
		
	}
	
	//TODO this how we test exceptions: @Test(expected = NumberFormatException.class)
	
	//test getters
	@Test
	public void testGetName() {
		Player player = new Player(null, "Ehud", 1);
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
	}
	
	@Test
	public void testGetPlayerNum() {
		Player player = new Player(null, "Ehud", 1);
		assertEquals("Player's number should be 1", 1, player.getPlayerNum());
	}
	
	@Test
	public void testSetName(){
		Player player = new Player(null, "Ehud", 1);
		assertEquals("Player's name should be Ehud", "Ehud", player.getName());
		player.setName("Ilan");
		assertEquals("Player's name should be Ilan", "Ilan", player.getName());
	}

}
