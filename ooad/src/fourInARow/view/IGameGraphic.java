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
	
	
	@Override
	public int hashCode();
	
	/**
	 * Implement such that two discs with the same graphics are equal
	 * regardless of position
	 * @return
	 */
	@Override
	public boolean equals(Object obj);
}
