package UnitTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import FourInARow.view.CompositeGraphics;

public class TestBoardGraphics {

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
	public final void testAddGraphics() {
		CompositeGraphics cg = new CompositeGraphics();
		CompositeGraphics newcg = new CompositeGraphics(); 
		cg.addGraphics(newcg);
		assertSame("", cg.getGraphics(0), newcg);
	}

	@Test (expected = java.lang.IndexOutOfBoundsException.class)	
	public final void testGetGraphics() {
		CompositeGraphics cg = new CompositeGraphics();
		CompositeGraphics newcg = new CompositeGraphics(); 
		cg.addGraphics(newcg);
		assertSame("", cg.getGraphics(1), newcg);	
	}

}
