package fourInARow.defaultImplementation;


import fourInARow.excpetion.InvalidPlayerNumException;
import fourInARow.view.DiscFactory;
import fourInARow.view.CompositeGraphic;

public class BoardGraphic extends CompositeGraphic {

	private static final char CELL_BORDER = '|';
	private static final char BOARD_BOTTOM = '-';
	private static final char EMPTY_CELL = ' ';


	public BoardGraphic(DiscFactory discFactory) {
		try {
			_graphics.add(discFactory.getDisc(1));
			_graphics.add(discFactory.getDisc(2));
		}
		catch (InvalidPlayerNumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void drawGraphic(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.print(CELL_BORDER);
			for (int j = 0; j < board[0].length; j++) {
				
				if (board[i][j] == 1) {
					_graphics.get(0).drawGraphic(null);
				} else if (board[i][j] == 2) {
					_graphics.get(1).drawGraphic(null);
				} else {
					System.out.print(EMPTY_CELL);
				}
				System.out.print(CELL_BORDER);
			}
			System.out.println();
		}
		for (int i = 0; i < board[0].length; i++) {
			System.out.print(" " + BOARD_BOTTOM);
		}
		System.out.println();
	}

	@Override
	public BoardGraphic clone() {
		return null;
	}

}
