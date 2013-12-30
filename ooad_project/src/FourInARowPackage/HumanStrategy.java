package FourInARowPackage;

import java.util.Scanner;


public class HumanStrategy implements PlayerStrategy{

	@Override
	public int makeMove(FourInARowModel model) {
		Scanner terminalInput = new Scanner(System.in);
		int col = Integer.parseInt(terminalInput.nextLine());
		terminalInput.close();
		return col;
	}
	
	public void printWinMessage(int playerNum){
		System.out.println("Game has ended! Player " + Integer.toString(playerNum) + " won!");
	}

}
