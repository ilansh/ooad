package fourInARow.controller;

import java.util.ArrayList;
import java.util.Scanner;

import fourInARow.excpetion.ColumnFullException;
import fourInARow.excpetion.ColumnOutOfRangeException;
import fourInARow.model.FourInARowModel;
import fourInARow.model.FourInARowModel.GameStatus;
import fourInARow.view.FourInARowView;

public class SimpleFourInARowController extends FourInARowController{
	
	
	// the main menu
	private static final int QUIT_KEY = 0;
	private static final int VS_HUMAN_KEY = 1;
	private static final int VS_COMPUTER_KEY = 2; //TODO: char or int
	private static final int NUM_OF_PLAYERS = 2;
	
	
	public SimpleFourInARowController(FourInARowModel model, FourInARowView view){
		_model = model;
		_views = new ArrayList<FourInARowView>();
		addView(view);
		_players = new ArrayList<PlayerStrategy>(NUM_OF_PLAYERS);
	}
	
	public SimpleFourInARowController(FourInARowModel model, ArrayList<FourInARowView> views){
		_model = model;
		_views = new ArrayList<FourInARowView>();
		for(int i = 0; i < views.size(); i++) {
			addView(views.get(i));
		}
	}

//	@Override
//	public void showMenu() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	protected void printInitMenu() {
		System.out.println(Integer.toString(QUIT_KEY) + ". Exit");
		System.out.println(Integer.toString(VS_HUMAN_KEY) + ". Play against a friend");
		System.out.println(Integer.toString(VS_COMPUTER_KEY) + ". Play against the computer");
		System.out.print("Please choose an option:");
	}
	
	
	public void gameLoop(){
		Scanner terminalInput = new Scanner(System.in);
		chooseGameType(terminalInput);
		initViews();
		FourInARowModel.GameStatus gameStatus = FourInARowModel.GameStatus.CONTINUE;
		int currentPlayer = 0;
		int col;
		
		do {
			System.out.print("Player " + Integer.toString(currentPlayer + 1) + ", choose a column: "); //TODO move to other location
			try {
				col = _players.get(currentPlayer % NUM_OF_PLAYERS).makeMove(_model); // TODO: no exception handling...
				gameStatus =  _model.addDisc(col, currentPlayer + 1);
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
			_players.get((currentPlayer + 1) % NUM_OF_PLAYERS).printWinMessage((currentPlayer + 1) % NUM_OF_PLAYERS + 1);
		}
		else if (gameStatus == FourInARowModel.GameStatus.DRAW){ //TODO erase
			System.out.print("Board is full! game has ended with a tie!");
		}
		
	}
	
	
	protected void chooseGameType(Scanner terminalInput) {
		int choice;
		printInitMenu();
		choice = Integer.parseInt(terminalInput.nextLine());
		System.out.println(choice);
	    while(choice != QUIT_KEY && choice != VS_HUMAN_KEY && choice != VS_COMPUTER_KEY)
	    {
			 System.out.println("Input incorrect! Please try again.");
			 printInitMenu();
			 choice = Integer.parseInt(terminalInput.nextLine());
	    }
		
		_players.add(0, new HumanStrategy());
		if (choice == VS_HUMAN_KEY){
			_players.add(0, new HumanStrategy());
		}
		else if (choice == VS_COMPUTER_KEY){
			_players.add(1, new SimpleComputerStrategy());
		}
		else if (choice == QUIT_KEY){
			System.exit(0); //TODO: organized exit
		}
	}
	
}
