package fourInARow.defaultImplementation;

import fourInARow.view.CompositeGraphic;
import fourInARow.view.IGameGraphic;



public class DecoratedDisc extends CompositeGraphic {

	@Override
	public void drawGraphic(int[][] board) {
		System.out.print("*");
		super.drawGraphic(board); //draw children
	}

	@Override
	public IGameGraphic clone() {
		return new DecoratedDisc();
	}

//	@Override
//	public boolean equals(Object other) {
//		DiscGraphic dg = (DiscGraphic) other;
//		if (this._cellContent == dg._cellContent) {
//			return true;
//		}
//		return false;
//	}
}
