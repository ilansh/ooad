package fourInARow.unitTests;

import static org.junit.Assert.*;

import org.junit.*;

import fourInARow.excpetion.*;
import fourInARow.model.*;

public class ModelTest {
	
	private MyModel _model;
	
	
	@Before
	public void setUp(){
		_model = new MyModel(5, 6);
	}
	
	@After
	public void tearDown(){
		//run after each test
		
	}
	
	//test getters
	@Test
	public void testGetCols() throws NullArgumentNotPermittedException {
		MyModel model = _model;
		assertEquals("Number of board columns should be 5", 5, model.getNumCols());
	}
	
	@Test
	public void testGetRows() {
		MyModel model = _model;
		assertEquals("Number of board columns should be 6", 6, model.getNumRows());
	}
	
	@Test
	public void testGetBoard(){
		MyModel model = _model;
		int[][] board1 = model.getBoard();
		assertEquals("Number of board rows should be 6", 6, board1.length);
		assertEquals("Number of board columns should be 5", 5, board1[0].length);
		int[][] board2 = model.getBoard();
		assertFalse("Boards shouldn't be identical", board1 == board2);
	}
	
	//test firstEmptyRow function
	@Test
	public void testFirstEmptyRow() throws ColumnFullException{
		MyModel model = _model;
		//TODO
		//assertEquals("First empty row must be " + (model.getNumRows()-1), (model.getNumRows()-1), model.firstEmptyRow(0));
		assertEquals("First empty row must be 5", 5, model.firstEmptyRow(0));
	}
	
	//TODO addDisc tests - error with aspectJ
	
	//TODO error with aspectJ! what to do?!?!?!?!?!?!?
	@Test (expected = ColumnFullException.class)
	public void testFirstEmptyRowNotValid() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(2, 1);
		GameStatus status; 
		status = model.addDisc(0, 1);
		model.firstEmptyRow(0);
	}
	

}
