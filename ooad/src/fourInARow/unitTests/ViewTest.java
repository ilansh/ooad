package fourInARow.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import fourInARow.view.CompositeGraphic;
import fourInARow.view.IGameGraphic;
import fourInARow.view.View;


public class ViewTest {
	
	class GameGraphicTester extends CompositeGraphic{

		@Override
		public void drawGraphic(int[][] board) {
			
			
		}
		
		public IGameGraphic clone() {
			return null;
		}
		
		public ArrayList<IGameGraphic> getChildren() {
			return _graphics;
		}
	}
	
	class ViewTester extends View {
		
		public ViewTester(CompositeGraphic gameView) {
			super(gameView);
		}

		public CompositeGraphic getRoot() {
			return _gameView;
		}
	}
	
	
	@Test
	public void testDecorateNullFather(){
		GameGraphicTester decorator = new GameGraphicTester();
		GameGraphicTester decoratee = new GameGraphicTester();
		ViewTester view = new ViewTester(decoratee);
		view.decorate(null, decorator, decoratee);
		assertTrue("Decoratee should be decorator's child", decorator.getChildren().contains(decoratee));
		assertEquals("Decorator should be root of composite tree", view.getRoot(), decorator);
	}
	
	@Test
	public void testDecorateExistFather(){
		GameGraphicTester decorator = new GameGraphicTester();
		GameGraphicTester decoratee = new GameGraphicTester();
		GameGraphicTester father = new GameGraphicTester();
		ViewTester view = new ViewTester(father);
		view.decorate(father, decorator, decoratee);
		assertTrue("Decoratee should be decorator's child", decorator.getChildren().contains(decoratee));
		assertTrue("Decorator should be son of father", father.getChildren().contains(decorator));
	}
	
	@Test
	public void testToString(){
		View view = new View(new GameGraphicTester());
		assertTrue("Return value should be String" , view.toString() instanceof String);
	}
	
	@Test
	public void testUpdate(){
		View view = new View(new GameGraphicTester());
		view.update(null, null);
	}

}
