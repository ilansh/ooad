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
		MyModel model = new MyModel(1,4);
		assertEquals("Board isn't full", false, model.isBoardFull());
		for(int i=0; i<4; i++){
			try {
				GameStatus status = model.addDisc(i, 1);
			} catch (ColumnFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ColumnOutOfRangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals("Board isn't full", false, model.isBoardFull());
	}

}
