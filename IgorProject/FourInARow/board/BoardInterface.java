package FourInARow.board;

public interface BoardInterface {
	
	public void setCell(BoardCell type, int col, int row);
	
	public int getCell(int col, int row);
	
	public int firstEmptyRow(int col) ;
	
	public boolean isColFull(int col);
	
	public int getRowSize();
	
	public int getColSize();
	
	public int getCount();
	
	public int[][] getBoard();
	

}
