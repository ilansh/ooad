package fourInARow.controller;

import java.util.Scanner;

import fourInARow.model.FourInARowModel;


public class HumanStrategy implements PlayerStrategy{

	Scanner _humanInput;
	
	public HumanStrategy() {
		_humanInput = new Scanner(System.in);
	}
	
	@Override
	public int makeMove(FourInARowModel model) throws NumberFormatException{
		int col = Integer.parseInt(_humanInput.nextLine());
		return col;
	}
	
	@Override
	public void printWinMessage(int playerNum){
		System.out.println("Game has ended! Player " + Integer.toString(playerNum) + " won!");
	}

	@Override
	public void printMoveMessage(int playerNum) {
		System.out.println("Player " + playerNum + ", choose a column: ");
	}


}
