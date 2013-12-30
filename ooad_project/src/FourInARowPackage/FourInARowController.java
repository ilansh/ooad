package FourInARowPackage;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class FourInARowController {

	//constants
	
	private static final int NUM_OF_PLAYERS = 2;
	
	
	// the main menu
	protected int _quitKey;
	protected int _vsHumanKey;;
	protected int _vsComputerKey;
	
	protected FourInARowModel _model;
	protected ArrayList<FourInARowView> _views;
	protected PlayerStrategy _players[];
	
	
	protected abstract void showMenu();
	
	protected abstract void printInitMenu();
	
	protected void chooseGameType(Scanner terminalInput) {
		int choice;
		
		do  { 
			choice = Integer.parseInt(terminalInput.nextLine());
			System.out.println("Input incorrect! Please try again.");
		} while(choice != _quitKey && choice != _vsHumanKey && choice != _vsComputerKey);
		
		_players[0] = new HumanStrategy();
		if (choice == _vsHumanKey){
			_players[1] = new HumanStrategy();
		}
		else if (choice == _vsComputerKey){
			_players[1] = new SimpleComputerStrategy();
		}
		else if (choice == _quitKey){
			return; //TODO: organized exit
		}
	}
	
	public void gameLoop(){
		Scanner terminalInput = new Scanner(System.in);
		printInitMenu();
		chooseGameType(terminalInput);
		FourInARowModel.GameStatus gameStatus = FourInARowModel.GameStatus.CONTINUE;
		int currentPlayer = 1;
		int col;
		
		do {
			System.out.print("Player " + Integer.toString(currentPlayer) + ", choose a column: "); //TODO move to other location
			try {
				col = _players[currentPlayer % 2].makeMove(_model); // TODO: no exception handling...
				gameStatus =  _model.addDisc(col, currentPlayer);
				currentPlayer ++;
				currentPlayer %= NUM_OF_PLAYERS;
			}
			catch(ColumnFullException cfe) {
				//TODO: handle
			}
			catch(ColumnOutOfRangeException coore) {
				//TODO: handle
			}
		} while (gameStatus == FourInARowModel.GameStatus.CONTINUE);
		
		if (gameStatus == FourInARowModel.GameStatus.WIN){
			_players[(currentPlayer+1)%NUM_OF_PLAYERS].printWinMessage(currentPlayer);
		}
		else if (gameStatus == FourInARowModel.GameStatus.DRAW){ //TODO erase
			System.out.print("Board is full! game has ended with a tie!");
		}
		
	}
	
	
//		System.out.println(Integer.toString(QUIT) + ". Exit");
//		System.out.println(Integer.toString(VS_HUMAN) + ". Play against a friend");
//		System.out.println(Integer.toString(VS_COMPUTER) + ". Play against the computer");
//		System.out.print("Please choose an option:");
		
}
