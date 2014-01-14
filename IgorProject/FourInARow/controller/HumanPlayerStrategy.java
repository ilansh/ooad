package FourInARow.controller;

import java.util.Scanner;

//import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;


public class HumanPlayerStrategy implements Strategy{

	Scanner _input = new Scanner(System.in);
	
	@Override
	public int makeMove(ModelInterface model) throws NumberFormatException{
		System.out.println("Choose a column to put a disk");
		int col = Integer.parseInt(_input.nextLine());
		
		return col;
	}
	
	public void setScanner(Scanner scanner){
		_input = scanner;
	}

	
}
