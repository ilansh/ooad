

package FourInARow.controller;


import java.util.Random;
import java.util.Scanner;

//import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;


public class SimpleComputerPlayerStrategy extends ComputerPlayerStrategy{

	//igor
	//int _colSize;
	
	
	public SimpleComputerPlayerStrategy(int colSize){
		super(colSize);
		//_colSize = colSize;
	}
	
	
	//simple computerStrategy - picks first possible legal column in an ascending order
	
	@Override
	public int makeMove(ModelInterface model) throws NumberFormatException{

		Random generator = new Random(); 
		//check this
		//generator.nextInt(this._colSize);
		//igor
		int choice =  (this._colSize) - generator.nextInt(this._colSize);
		return choice;
		
	}


	@Override
	public void setScanner(Scanner sc) { }
	
}


