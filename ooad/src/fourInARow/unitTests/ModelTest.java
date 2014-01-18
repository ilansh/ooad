package fourInARow.unitTests;

import static org.junit.Assert.*;

import org.junit.*;

import fourInARow.excpetion.ColumnFullException;
import fourInARow.excpetion.ColumnOutOfRangeException;
import fourInARow.model.GameStatus;
import fourInARow.model.MyModel;

public class ModelTest {

	@Before
	public void setUp(){
		//run before each test
	}
	
	@After
	public void tearDown(){
		//run after each test
		
	}
	
	//TODO this how we test exceptions: @Test(expected = NumberFormatException.class)
	
	
	@Test
	public void testGetters() {
		MyModel model = new MyModel(5,6);
		assertEquals("Number of board columns should be 5", 5, model.getNumCols());
		assertEquals("Number of board columns should be 6", 6, model.getNumRows());
	}
	
	@Test
	public void testBoardFull(){

	}

}
