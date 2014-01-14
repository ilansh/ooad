package UnitTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import FourInARow.board.Board;
import FourInARow.board.BoardCell;
import FourInARow.model.FourInARowModel;
import FourInARow.model.GameState;
import FourInARow.model.ModelInterface;

public class TestFourInARowModel {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testFourInARowModelIntIntInt() {
		//testing public FourInARowModel(int colSize, int rowSize, int disksToWin)
		FourInARowModel model = new FourInARowModel(6,5,4);
		/*_board = new Board(colSize, rowSize);
		DISKSTOWIN = disksToWin+1;*/
		assertEquals("Col size should be 6",6, model.getBoardObject().getColSize());
		assertEquals("Row size should be 5",5, model.getBoardObject().getRowSize());
		assertEquals("Disk to win should be 5",5, model.getDiskToWin());
	}

	@Test
	public final void testFourInARowModelModelInterface() {
		//testing cpy ctr public FourInARowModel(ModelInterface model)
		ModelInterface model = new FourInARowModel(6,5,4);
		FourInARowModel newmodel = new FourInARowModel(model);
		assertEquals("Col size should be 6",6, newmodel.getBoardObject().getColSize());
		assertEquals("Row size should be 5",5, newmodel.getBoardObject().getRowSize());
		assertEquals("Disk to win should be 5",5, newmodel.getDiskToWin());
		assertEquals("Turn should be equal", newmodel.getTurn(), model.getTurn());
		assertEquals("Game state should be equal", newmodel.getGameState(), model.getGameState());
		assertEquals("DISKSTOWIN should be equal", newmodel.getDiskToWin(), ((FourInARowModel)model).getDiskToWin());
		Board board = ((FourInARowModel)model).getBoardObject();//TODO cpyc dosn't clone Board
		Board newboard = newmodel.getBoardObject();
		assertNotSame("boards should be the same", board, newboard);
	}

	@Test
	public final void testUpdate() {
		// TODO
	}

	@Test
	public final void testInitGame() {
		//_discsNum = 0;
		ModelInterface model = new FourInARowModel(6,5,4);
		model.initGame();
		assertEquals("Game state should be GameState.PROGRESS",GameState.PROGRESS, model.getGameState());
		assertEquals("Turn should be 0+1",1, model.getTurn()); 
	}

	@Test
	public final void testIsWinnerDisk() {
		FourInARowModel model = new FourInARowModel(6,5,4);
		model.initGame();
		model.getBoardObject().setCell(BoardCell.COLORONE, 1, 0);
		System.out.println(FourInARowModel.isWinnerDisk(model, 1, 0));
		//assertFalse("isWinnerDisk should return false", FourInARowModel.isWinnerDisk(model, 1, 1));
		//TODO
	}

}
