
package FourInARow.controller;


//import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;


public abstract class ComputerPlayerStrategy implements Strategy{

	//igor
	protected int _colSize;
	
	
	public ComputerPlayerStrategy(int colSize){
		_colSize = colSize;
	}
	
	
	//default computerStrategy - picks random column 0 to colSize
	
	@Override
	public abstract int makeMove(ModelInterface model);
	
	
}


