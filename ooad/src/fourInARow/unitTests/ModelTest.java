package fourInARow.unitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Observable;

import org.junit.*;

import fourInARow.aspects.GameLogger;
import fourInARow.excpetion.*;
import fourInARow.model.*;

public class ModelTest {
	
	private IModel _model;
	
	private static PrintWriter _testWriter;
	
	
	@Before
	public void setUp(){
		_model = new MyModel(5, 6);
		try {
			_testWriter = new PrintWriter("testGame.log");
			GameLogger.initLogStream(_testWriter);
		} catch (FileNotFoundException e) {
			fail("Can't instanciate logger");
		}
	}
	
	@After
	public void tearDown(){
		_testWriter.close();
		
	}
	
	//test getters
	@Test
	public void testGetCols() throws NullArgumentNotPermittedException {
		IModel model = _model;
		assertEquals("Number of board columns should be 5", 5, model.getNumCols());
	}
	
	@Test
	public void testGetRows() {
		IModel model = _model;
		assertEquals("Number of board columns should be 6", 6, model.getNumRows());
	}
	
	@Test
	public void testGetBoard(){
		IModel model = _model;
		int[][] board1 = model.getBoard();
		assertEquals("Number of board rows should be 6", 6, board1.length);
		assertEquals("Number of board columns should be 5", 5, board1[0].length);
		int[][] board2 = model.getBoard();
		assertFalse("Boards shouldn't be identical", board1 == board2);
	}

	@Test
	public void testFirstEmptyRowFirstRow() throws ColumnFullException{
		IModel model = _model;
		assertEquals("First empty row must be 5", 5, model.firstEmptyRow(0));
	}
	
	@Test
	public void testFirstEmptyRowLastRow() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		assertEquals("First empty row must be 0", 0, model.firstEmptyRow(0));
	}
	
	@Test
	public void testFirstEmptyRowMidRow() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		_model.addDisc(0, 1);
		_model.addDisc(0, 1);
		assertEquals("First empty row must be 3", 3, model.firstEmptyRow(0));
	}
	
	@Test (expected = ColumnFullException.class)
	public void testFirstEmptyRowColumnFull() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = new MyModel(8, 1);
		model.addDisc(0, 1);
		model.firstEmptyRow(0);
	}
	
	@Test 
	public void testAddDiscWinner() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = new MyModel(4, 1);
		GameStatus status; 
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		model.addDisc(2, 1);
		status = model.addDisc(3, 1);
		assertEquals("Status should be WIN", GameStatus.WIN, status);
	}
	
	@Test 
	public void testAddDiscOngoing() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = new MyModel(4, 1);
		GameStatus status; 
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		status = model.addDisc(3, 1);
		assertEquals("Status should be ONGOING", GameStatus.ONGOING, status);
	}
	
	@Test 
	public void testAddDiscDraw() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = new MyModel(4, 1);
		GameStatus status; 
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		model.addDisc(2, 2);
		status = model.addDisc(3, 1);
		assertEquals("Status should be DRAW", GameStatus.DRAW, status);
	}
	
	@Test (expected = ColumnOutOfRangeException.class)
	public void testAddDiscOutOfRangePos() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = new MyModel(4, 1);
		model.addDisc(4, 1);
	}
	
	@Test (expected = ColumnOutOfRangeException.class)
	public void testAddDiscOutOfRangeNeg() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = new MyModel(4, 1);
		model.addDisc(-1, 1);
	}
	
	@Test (expected = ColumnOutOfRangeException.class)
	public void testAddDiscOutOfRangeIntMax() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = new MyModel(4, 1);
		model.addDisc(Integer.MAX_VALUE, 1);
	}
	
	@Test
	public void testIsWinnerHorizontal0() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		model.addDisc(2, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 3, 5, 1));
	}
	
	@Test
	public void testIsWinnerHorizontal1() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 1);
		model.addDisc(2, 1);
		model.addDisc(3, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 1, 5, 1));
	}
	
	@Test
	public void testIsWinnerHorizontal2() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 1);
		model.addDisc(1, 1);
		model.addDisc(3, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 2, 5, 1));
	}
	
	@Test
	public void testIsWinnerHorizontal3() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(3, 1);
		model.addDisc(1, 1);
		model.addDisc(2, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 0, 5, 1));
	}
	
	@Test
	public void testIsWinnerVertical0() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 1);
		model.addDisc(0, 1);
		model.addDisc(0, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 0, 2, 1));
	}
	
	@Test
	public void testIsWinnerVertical1() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 1);
		model.addDisc(0, 2);
		model.addDisc(0, 1);
		model.addDisc(0, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 0, 4, 1));
	}
	
	@Test
	public void testIsWinnerVertical2() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 1);
		model.addDisc(0, 1);
		model.addDisc(0, 2);
		model.addDisc(0, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 0, 3, 1));
	}
	
	@Test
	public void testIsWinnerVertical3() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 2);
		model.addDisc(0, 1);
		model.addDisc(0, 1);
		model.addDisc(0, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 0, 5, 1));
	}
	
	/*
	 *       
	 *     x o
	 *   x o o
	 * x o o o 
	 */
	@Test
	public void testIsWinnerDiagRight0() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(3, 2);
		model.addDisc(1, 1); //row
		model.addDisc(2, 1); //row
		model.addDisc(0, 1); //row
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 3, 2, 1));
	}
	
	/*
	 *       x
	 *       o
	 *   x o o
	 * x o o o 
	 */
	@Test
	public void testIsWinnerDiagRight1() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(3, 2);
		model.addDisc(1, 1);
		model.addDisc(0, 1);
		model.addDisc(3, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 2, 3, 1));
	}
	
	/*
	 *       x
	 *     x o
	 *     o o
	 * x o o o 
	 */
	@Test
	public void testIsWinnerDiagRight2() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(3, 2);
		model.addDisc(0, 1);
		model.addDisc(2, 1);
		model.addDisc(3, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 1, 4, 1));
	}
	
	/*
	 *       x
	 *     x o
	 *   x o o
	 *   o o o 
	 */
	@Test
	public void testIsWinnerDiagRight3() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(1, 1);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(2, 1);
		model.addDisc(3, 2);
		model.addDisc(3, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 0, 5, 1));
	}
	
	/*
	 *  
	 *  o x 
	 *  o o x
	 *  o o o x 
	 */
	@Test
	public void testIsWinnerDiagLeft0() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(0, 2);
		model.addDisc(3, 1);
		model.addDisc(2, 1);
		model.addDisc(1, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 0, 2, 1));
	}
	
	/*
	 *  x
	 *  o  
	 *  o o x
	 *  o o o x 
	 */
	@Test
	public void testIsWinnerDiagLeft1() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(0, 2);
		model.addDisc(3, 1);
		model.addDisc(2, 1);
		model.addDisc(0, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 1, 3, 1));
	}
	
	/*
	 *  x
	 *  o x 
	 *  o o 
	 *  o o o x 
	 */
	@Test
	public void testIsWinnerDiagLeft2() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(0, 2);
		model.addDisc(3, 1);
		model.addDisc(1, 1);
		model.addDisc(0, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 2, 4, 1));
	}
	
	/*
	 *  x
	 *  o x 
	 *  o o x
	 *  o o o  
	 */
	@Test
	public void testIsWinnerDiagLeft3() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(0, 2);
		model.addDisc(1, 2);
		model.addDisc(0, 2);
		model.addDisc(2, 1);
		model.addDisc(1, 1);
		model.addDisc(0, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 3, 5, 1));
	}
	
	/*    x
	 *    o x
	 *    o o 
	 *    o o o x
	 *    o o o o 
	 */
	@Test
	public void testIsWinnerDiagLeft4() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(1, 2);
		model.addDisc(1, 2);
		model.addDisc(1, 2);
		model.addDisc(1, 2);
		model.addDisc(2, 2);
		model.addDisc(2, 2);
		model.addDisc(2, 2);
		model.addDisc(3, 2);
		model.addDisc(3, 2);
		model.addDisc(4, 2);
		model.addDisc(1, 1);
		model.addDisc(2, 1);
		model.addDisc(4, 1);
		assertTrue("isWinner should be true", model.isWinner(model.getBoard(), 3, 3, 1));
	}
	
	@Test
	public void testGetObservable(){
		IModel model = _model;
		assertTrue("isWinner should be true", model.getObservable() instanceof Observable);
	}
	
	@Test
	public void testToString() throws ColumnFullException, ColumnOutOfRangeException{
		IModel model = _model;
		model.addDisc(0, 2);
		model.addDisc(1, 1);
		String s = model.toString();
		assertTrue("isWinner should be true",  s.matches("(.|\n)*o(.|\n)*x(.|\n)*"));
	}
	
	

}
