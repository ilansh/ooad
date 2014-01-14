package UnitTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import FourInARow.controller.SimpleComputerPlayerStrategy;
import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;

public class TestSimpleComputerStrategy {

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
	public final void testMakeMove() {
		int col = 6;
		ModelInterface model = new FourInARowModel(col, 5, 4);
		SimpleComputerPlayerStrategy cs = new SimpleComputerPlayerStrategy(col);
		assertTrue("makeMove should return number larger then 0", cs.makeMove(model) >= 0);
		assertTrue("makeMove should return number samller then col", cs.makeMove(model) <= col);
	}
	

}
