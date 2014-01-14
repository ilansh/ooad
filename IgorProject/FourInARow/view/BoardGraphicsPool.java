package FourInARow.view;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BoardGraphicsPool {

	
	private static Queue<BoardGraphics> _pool;
	
	
	public static void initPool(){
		_pool =  new ConcurrentLinkedQueue<BoardGraphics>();
	}
	
	public static BoardGraphics getBoard(Graphics colorOne, Graphics colorTwo, 
			Graphics empty, Graphics boarder, Graphics newline){
		for (BoardGraphics g: _pool){
			if(g.getColorOne().equals(colorOne) && g.getColorTwo().equals(colorTwo) 
					&& g.getEmpty().equals(empty) && g.getNewLine().equals(newline) && g.getBoarder().equals(boarder))
				return g;
		}
		return null;
	}

	
	public static void addBoard(BoardGraphics cell){
		_pool.add(cell);
	}
}
