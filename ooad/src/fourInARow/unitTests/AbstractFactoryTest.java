package fourInARow.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import fourInARow.excpetion.InvalidPlayerNumException;
import fourInARow.excpetion.NullArgumentNotPermittedException;
import fourInARow.view.AbstractDiscFactory;
import fourInARow.view.DiscFactory;
import fourInARow.view.IGameGraphic;

public class AbstractFactoryTest {

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
	
	@Test
	public void testNewInstanceFirstTime() {
		AbstractDiscFactory adf = AbstractDiscFactory.newInsance();
		assertNotNull("Should not be null", adf);
	}
	
	@Test
	public void testNewInstanceSecondTime() { //singleton test
		AbstractDiscFactory adf1 = AbstractDiscFactory.newInsance();
		AbstractDiscFactory adf2 = AbstractDiscFactory.newInsance();
		assertNotNull("Should not be null", adf1);
		assertEquals("adf1 should be equals to adf2", adf1, adf2);
	}
	
	@Test (expected = NullArgumentNotPermittedException.class)
	public void testGetFactoryImplDisc1Null() throws NullArgumentNotPermittedException { //singleton test
		AbstractDiscFactory adf1 = AbstractDiscFactory.newInsance();
		adf1.getFactoryImpl(null, new TestGraphic('x'));
	}
	
	@Test (expected = NullArgumentNotPermittedException.class)
	public void testGetFactoryImplDisc2Null() throws NullArgumentNotPermittedException{ //singleton test
		AbstractDiscFactory adf1 = AbstractDiscFactory.newInsance();
		adf1.getFactoryImpl(new TestGraphic('x') , null);
	}
	
	@Test
	public void testGetFactoryImplnewImpl() throws NullArgumentNotPermittedException, InvalidPlayerNumException{ //singleton test
		AbstractDiscFactory adf1 = AbstractDiscFactory.newInsance();
		TestGraphic disc1 = new TestGraphic('x');
		DiscFactory df = adf1.getFactoryImpl(disc1 , new TestGraphic('o'));
		assertNotNull("df should not be null", df);
	}
	
	@Test
	public void testGetFactoryImplexistingImpl() throws NullArgumentNotPermittedException, InvalidPlayerNumException{ //singleton test
		AbstractDiscFactory adf1 = AbstractDiscFactory.newInsance();
		TestGraphic disc1 = new TestGraphic('x');
		TestGraphic disc2 = new TestGraphic('o');
		DiscFactory df = adf1.getFactoryImpl(disc1, disc2);
		DiscFactory df2 = adf1.getFactoryImpl(disc1, disc2);
		assertNotNull("df should not be null", df);
		assertEquals("df should be equal to df2 (same factory)", df, df2);
	}
	

}
