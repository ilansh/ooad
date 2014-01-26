package fourInARow.unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fourInARow.excpetion.InvalidPlayerNumException;
import fourInARow.view.DiscFactory;
import fourInARow.view.IGameGraphic;

public class DiscFactoryTest {

	private DiscFactory _df;
	private TestGraphic _d1;
	private TestGraphic _d2;
	
	class TestGraphic implements IGameGraphic {

		private char _c;
		
		public TestGraphic (char c) {
			_c = c;
		}
		
		@Override
		public void drawGraphic(int[][] board) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public TestGraphic clone() {
			return new TestGraphic(_c);
		}
		
		@Override
		public int hashCode() {
			return 37 * (int)_c;
		}
		
		@Override
		public boolean equals(Object other) {
			TestGraphic o = (TestGraphic)other;
			if(_c == o._c) {
				return true;
			}
			return false;
		}
	}
	
	@Before
	public void setUp() {
		_d1 = new TestGraphic('x');
		_d2 = new TestGraphic('o');
		_df = new DiscFactory(_d1, _d2);
	}
	
	@Test (expected = InvalidPlayerNumException.class)
	public void testGetDiscPlayerInvalidPlayerNum() throws InvalidPlayerNumException {
		_df.getDisc(3);
		
	}
	
	@Test
	public void testGetDiscPlayer1() throws InvalidPlayerNumException {
		IGameGraphic t = _df.getDisc(1);
		assertTrue("discs should be equal", t.equals(_d1));
		
	}
	
	@Test
	public void testGetDiscPlayer2() throws InvalidPlayerNumException {
		IGameGraphic t = _df.getDisc(2);
		assertTrue("discs should be equal", t.equals(_d2));
		
	}

}
