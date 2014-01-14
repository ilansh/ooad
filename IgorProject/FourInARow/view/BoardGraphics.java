package FourInARow.view;

import FourInARow.board.BoardCell;


public class BoardGraphics extends CompositeGraphics implements Graphics {

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((_boarder == null) ? 0 : _boarder.hashCode());
		result = prime * result
				+ ((_colorOne == null) ? 0 : _colorOne.hashCode());
		result = prime * result
				+ ((_colorTwo == null) ? 0 : _colorTwo.hashCode());
		result = prime * result + ((_empty == null) ? 0 : _empty.hashCode());
		result = prime * result
				+ ((_newline == null) ? 0 : _newline.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardGraphics other = (BoardGraphics) obj;
		if (_boarder == null) {
			if (other._boarder != null)
				return false;
		} else if (!_boarder.equals(other._boarder))
			return false;
		if (_colorOne == null) {
			if (other._colorOne != null)
				return false;
		} else if (!_colorOne.equals(other._colorOne))
			return false;
		if (_colorTwo == null) {
			if (other._colorTwo != null)
				return false;
		} else if (!_colorTwo.equals(other._colorTwo))
			return false;
		if (_empty == null) {
			if (other._empty != null)
				return false;
		} else if (!_empty.equals(other._empty))
			return false;
		if (_newline == null) {
			if (other._newline != null)
				return false;
		} else if (!_newline.equals(other._newline))
			return false;
		return true;
	}


	private Graphics _colorOne;
	private Graphics _colorTwo; 
	private Graphics _empty;
	private Graphics _boarder;
	private Graphics _newline;
			
	private BoardGraphics(Graphics colorOne, Graphics colorTwo, Graphics empty, Graphics boarder, Graphics newline)
	{
		_colorOne = colorOne;
		_colorTwo = colorTwo;
		_empty = empty;
		_boarder = boarder;
		_newline = newline;
	}
	

	public static BoardGraphics getBoardInstance(Graphics colorOne, 
			Graphics colorTwo, Graphics empty, Graphics boarder, Graphics newline){
		BoardGraphics instance = BoardGraphicsPool.getBoard(colorOne,  colorTwo,  empty,  boarder,  newline);
		
		if(instance == null){
			instance = new BoardGraphics(colorOne,  colorTwo,  empty,  boarder,  newline);
			BoardGraphicsPool.addBoard(instance);
		}
		
		return instance;
	}
	
	
	public Graphics getColorOne(){
		return _colorOne;
	}
	
	public Graphics getColorTwo(){
		return _colorTwo;
	}
	
	public Graphics getEmpty(){
		return _empty;
	}
	
	public Graphics getBoarder(){
		return _boarder;
	}
	
	public Graphics getNewLine(){
		return _newline;
	}
	
	//igor add
	private int[][] rotateMatrixRight(int[][] matrix)
	{
		
	    int w = matrix.length;
	    int h = matrix[0].length;
	    int[][] ret = new int[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            ret[i][j] = matrix[w - j - 1][i];
	        }
	    }
	    return ret;
	}
	
	
	@Override
	public Object drawGraphics(int[][] board) {
		
		//igor
		board = rotateMatrixRight(board);
		
		for(int i = 0; i < board.length; i++) {
		
			_boarder.drawGraphics(board);
			for(int j = board[0].length-1; j >= 0 ; j--) {
				if(board[i][j] == BoardCell.COLORONE.ordinal()) {
					_colorOne.drawGraphics(board);
				}
				else if(board[i][j] == BoardCell.COLORTWO.ordinal()) {
					_colorTwo.drawGraphics(board);
				}
				else {
					_empty.drawGraphics(board);
				}
			
				_boarder.drawGraphics(board);
			}
		
			_newline.drawGraphics(board);
		}
		
		System.out.println();
		return null;
	}

}
