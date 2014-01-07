package fourInARow.view;


public class BoardGraphic extends CompositeGraphic {

	private static final char CELL_BORDER = '|';
	private static final char BOARD_BOTTOM = '-';
	
	
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			System.out.print(CELL_BORDER);
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 1) {
					_graphics.get(0).drawGraphic(board);
				}
				else if(board[i][j] == 2) {
					_graphics.get(1).drawGraphic(board);
				}
				else {
					_graphics.get(2).drawGraphic(board);
				}
				System.out.print(CELL_BORDER);
			}
			System.out.println();
		}
		for(int i = 0; i < board[0].length; i++) {
			System.out.print( " " + BOARD_BOTTOM);
		}
		System.out.println();
	}

}
