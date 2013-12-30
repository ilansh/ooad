package FourInARowPackage;

public class BoardGraphic extends CompositeGraphic {

	private static final char CELL_BORDER = '|';
	private static final char BOARD_BOTTOM = '-';
	
	private GameGraphic _disc1; 
	private GameGraphic _disc2; //TODO: should be flyweight
	private GameGraphic _emptyCell;
			
	public BoardGraphic(GameGraphic disc1, GameGraphic disc2, GameGraphic emptyCell)
	{
		_disc1 = disc1;
		_disc2 = disc2;
		_emptyCell = emptyCell;
	}
	
	
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
					_disc1.drawGraphic(board);
				}
				else if(board[i][i] == 2) {
					_disc2.drawGraphic(board);
				}
				else {
					_emptyCell.drawGraphic(board);
				}
				System.out.print(CELL_BORDER);
			}
			System.out.println();
		}
		for(int i = 0; i < board[0].length; i++) {
			System.out.print(BOARD_BOTTOM);
		}
	}

}
