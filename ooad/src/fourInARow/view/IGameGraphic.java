package fourInARow.view;


import java.awt.Point;

public interface IGameGraphic extends Cloneable{
	
	public void setLocation(Point p);
	
	public void drawGraphic(int[][] board);

	/**
	 * This clone method must return a deep copy of the given graphic
	 * @return
	 */
	public IGameGraphic clone();
}
