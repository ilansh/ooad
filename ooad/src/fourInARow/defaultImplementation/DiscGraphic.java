package fourInARow.defaultImplementation;


import fourInARow.view.IGameGraphic;

public class DiscGraphic implements IGameGraphic {

	private char _cellContent;

	public DiscGraphic(char cellContent) {
		_cellContent = cellContent;
	}

	@Override
	public void drawGraphic(int[][] board) {
		System.out.print(_cellContent);

	}

	@Override
	public DiscGraphic clone() {
		return new DiscGraphic(_cellContent);
	}

	@Override
	public int hashCode() {
		int hash = 37 * (int) _cellContent;
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		DiscGraphic dg = (DiscGraphic) other;
		if (this._cellContent == dg._cellContent) {
			return true;
		}
		return false;
	}
}
