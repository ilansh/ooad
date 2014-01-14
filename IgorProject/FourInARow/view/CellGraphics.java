package FourInARow.view;

//need this?????? figure out how to print cell type

public class CellGraphics implements Graphics {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _cellContent;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CellGraphics other = (CellGraphics) obj;
		if (_cellContent != other._cellContent)
			return false;
		return true;
	}

	private char _cellContent;
	
	private CellGraphics(char cellContent) {
		_cellContent = cellContent;
	}
	
	public static CellGraphics getCellInstance(char c){
		CellGraphics instance = CellGraphicsPool.getCell(c);
		
		if(instance == null){
			instance = new CellGraphics(c);
			CellGraphicsPool.addCell(instance);
		}
		
		return instance;
	}
	
	
	public char getContent(){
		return _cellContent;
	}

	@Override
	public Object drawGraphics(int[][] board) {
		System.out.print(_cellContent);
		//return _cellContent;
		return _cellContent;
	}

	@Override
	public void addGraphics(Graphics board) { }
	
	
	public String toString(){
		return Character.toString(_cellContent);
	}
}
