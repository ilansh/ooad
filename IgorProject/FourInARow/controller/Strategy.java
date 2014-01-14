package FourInARow.controller;

import java.util.Scanner;

//import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;

public interface Strategy {
	
	public int makeMove(ModelInterface _model);

	public void setScanner(Scanner sc);
	
}

