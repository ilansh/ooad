package FourInARow.view;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CellGraphicsPool {

	
	private static Queue<CellGraphics> _pool;
	
	
	public static void initPool(){
		_pool =  new ConcurrentLinkedQueue<CellGraphics>();
	}
	
	public static CellGraphics getCell(char cell){
		for (CellGraphics g: _pool){
			if(g.getContent() == (cell))
				return g;
		}
		return null;
	}

	
	public static void addCell(CellGraphics cell){
		_pool.add(cell);
	}
}
