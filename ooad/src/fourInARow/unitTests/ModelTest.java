package fourInARow.unitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.*;

import fourInARow.aspects.GameLogger;
import fourInARow.excpetion.*;
import fourInARow.model.*;

public class ModelTest {
	
	private MyModel _model;
	
	private static PrintWriter testWriter;
	
	
	@Before
	public void setUp(){
		_model = new MyModel(5, 6);
		try {
			testWriter = new PrintWriter("testGame.log");
			GameLogger.initLogStream(testWriter);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown(){
		//run after each test
		testWriter.close();
		
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

	@Test
	public void testFirstEmptyRowFirstRow() throws ColumnFullException{
		MyModel model = _model;
		assertEquals("First empty row must be 5", 5, model.firstEmptyRow(0));
	}
	
	@Test
	public void testFirstEmptyRowLastRow() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = _model;
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		assertEquals("First empty row must be 0", 0, model.firstEmptyRow(0));
	}
	
	@Test
	public void testFirstEmptyRowMidRow() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = _model;
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		assertEquals("First empty row must be 3", 3, model.firstEmptyRow(0));
	}
	
	@Test (expected = ColumnFullException.class)
	public void testFirstEmptyRowColumnFull() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(8, 1);
		model.addDisc(0, 1);
		model.firstEmptyRow(0);
	}
	
	@Test 
	public void testAddDiscWinner() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(4, 1);
		GameStatus status; 
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		model.addDisc(2, 1);
		status = model.addDisc(3, 1);
		assertEquals("Status should be WIN", GameStatus.WIN, status);
	}
	
	@Test 
	public void testAddDiscOngoing() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(4, 1);
		GameStatus status; 
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		status = model.addDisc(3, 1);
		assertEquals("Status should be ONGOING", GameStatus.ONGOING, status);
	}
	
	@Test 
	public void testAddDiscDraw() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(4, 1);
		GameStatus status; 
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		model.addDisc(2, 2);
		status = model.addDisc(3, 1);
		assertEquals("Status should be DRAW", GameStatus.DRAW, status);
	}
	
	@Test (expected = ColumnOutOfRangeException.class)
	public void testAddDiscOutOfRangePos() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(4, 1);
		model.addDisc(4, 1);
	}
	
	@Test (expected = ColumnOutOfRangeException.class)
	public void testAddDiscOutOfRangeNeg() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(4, 1);
		model.addDisc(-1, 1);
	}
	
	@Test (expected = ColumnOutOfRangeException.class)
	public void testAddDiscOutOfRangeIntMax() throws ColumnFullException, ColumnOutOfRangeException{
		MyModel model = new MyModel(4, 1);
		model.addDisc(Integer.MAX_VALUE, 1);
	}
	

}
