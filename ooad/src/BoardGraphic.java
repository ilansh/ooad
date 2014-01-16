


import java.awt.Point;

import fourInARow.model.PlayerNum;
import fourInARow.view.DiscFactory;
import fourInARow.view.CompositeGraphic;


public class BoardGraphic extends CompositeGraphic {

	private static final char CELL_BORDER = '|';
	private static final char BOARD_BOTTOM = '-';
	private static final char EMPTY_CELL = ' ';
	
	private DiscFactory _factory1;
	private DiscFactory _factory2;
	
	
	public BoardGraphic(DiscFactory factory1, DiscFactory factory2) {  //package private
		_factory1 = factory1;
		_factory2 = factory2;
	}
	
	
	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawGraphic(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			System.out.print(CELL_BORDER);
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == PlayerNum.PLAYER1.ordinal()) {
					_factory1.getDisc(i, j, PlayerNum.PLAYER1.ordinal()).drawGraphic(null);
				}
				else if(board[i][j] == PlayerNum.PLAYER2.ordinal()) {
					_factory2.getDisc(i, j, PlayerNum.PLAYER2.ordinal()).drawGraphic(null);
				}
				else {
					System.out.print(EMPTY_CELL);
//					_cellFactory.newInstance(i, j, PlayerNum.EMPTY.ordinal()).drawGraphic(null);
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
	
	@Override
	public BoardGraphic clone(){ 
		return null;
	}

}
