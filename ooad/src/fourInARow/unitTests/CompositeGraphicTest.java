package fourInARow.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fourInARow.view.CompositeGraphic;
import fourInARow.view.IGameGraphic;

public class CompositeGraphicTest {

	
	CompositeGraphicTester _cgtImpl;
	
	class CompositeGraphicTester extends CompositeGraphic {

		@Override
		public IGameGraphic clone() {
			return null;
		}
		
		public ArrayList<IGameGraphic> getChildren() {
			return _graphics;
		}
		
	}
	
	class LeafGraphicTester implements IGameGraphic {

		@Override
		public IGameGraphic clone() {
			return null;
		}
		
		@Override
		public void drawGraphic(int[][] board) {
			
		}
		
	}
	
	
	@Before
	public void setUp() {
		_cgtImpl = new CompositeGraphicTester();
	}
	
	@Test
	public void testAddGraphic() {
		IGameGraphic g = new CompositeGraphicTester();
		_cgtImpl.addGraphic(g);
		assertTrue(_cgtImpl.getChildren().contains(g));
		
	}
	
	@Test
	public void testRemoveGraphic() {
		IGameGraphic g = new CompositeGraphicTester();
		_cgtImpl.addGraphic(g);
		_cgtImpl.removeGraphic(g);
		assertEquals("size should be 0", 0, _cgtImpl.getChildren().size());
		
	}
	
	@Test
	public void testDrawGraphic() {
		_cgtImpl.addGraphic(new LeafGraphicTester());
		_cgtImpl.addGraphic(new LeafGraphicTester());
		_cgtImpl.drawGraphic(null);
		
	}
	
	@Test
	public void testToString() {
		_cgtImpl.toString();
		
	}

}
