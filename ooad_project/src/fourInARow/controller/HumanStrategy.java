package fourInARow.controller;

import java.util.Scanner;

import fourInARow.model.FourInARowModel;


public class HumanStrategy implements PlayerStrategy{

	Scanner _humanInput;
	
	public HumanStrategy() {
		_humanInput = new Scanner(System.in);
	}
	
	@Override
	public int makeMove(FourInARowModel model) {
		int col = Integer.parseInt(_humanInput.nextLine());
		return col;
	}
	
	public void printWinMessage(int playerNum){
		System.out.println("Game has ended! Player " + Integer.toString(playerNum) + " won!");
	}

}
