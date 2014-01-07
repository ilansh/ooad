package fourInARow.controller;

import java.util.ArrayList;
import java.util.Scanner;

import fourInARow.excpetion.ColumnFullException;
import fourInARow.excpetion.ColumnOutOfRangeException;
import fourInARow.model.FourInARowModel;
import fourInARow.model.FourInARowModel.GameStatus;
import fourInARow.view.BoardGraphic;
import fourInARow.view.BorderBoard;
import fourInARow.view.CellGraphic;
import fourInARow.view.FourInARowView;
import fourInARow.view.WindowGraphic;

public class SimpleFourInARowController implements GameController{
	
	
	protected FourInARowModel _model;
	protected ArrayList<FourInARowView> _views;
	protected ArrayList<PlayerStrategy> _players;
	
	
	// the main menu
	private static final int QUIT_KEY = 0;
	private static final int VS_HUMAN_KEY = 1;
	private static final int VS_COMPUTER_KEY = 2; //TODO: char or int
	private static final int NUM_OF_PLAYERS = 2;
	
	private Scanner _terminalInput;
	
	public SimpleFourInARowController(FourInARowModel model){
		_model = model;
		_views = new ArrayList<FourInARowView>();
		_terminalInput = new Scanner(System.in);
		_players = new ArrayList<PlayerStrategy>(NUM_OF_PLAYERS);
		BoardGraphic board = new BoardGraphic();
		board.addGraphic(new CellGraphic('x'));
		board.addGraphic(new CellGraphic('o'));
		board.addGraphic(new CellGraphic(' '));
		WindowGraphic window = new WindowGraphic();
		window.addGraphic(board);
		
		BorderBoard b = new BorderBoard();
		
		FourInARowView view = new FourInARowView(window);
		view.decorate(null, b, window);
		addView(view);
		
	}
	
	
	public SimpleFourInARowController(FourInARowModel model, FourInARowView view){
		_model = model;
		_views = new ArrayList<FourInARowView>();
		_terminalInput = new Scanner(System.in);
		addView(view);
		_players = new ArrayList<PlayerStrategy>(NUM_OF_PLAYERS);
	}
	
	public SimpleFourInARowController(FourInARowModel model, ArrayList<FourInARowView> views){
		_model = model;
		_views = new ArrayList<FourInARowView>();
		_terminalInput = new Scanner(System.in);
		for(int i = 0; i < views.size(); i++) {
			addView(views.get(i));
		}
	}

	protected void printInitMenu() {
		System.out.println(Integer.toString(QUIT_KEY) + ". Exit");
		System.out.println(Integer.toString(VS_HUMAN_KEY) + ". Play against a friend");
		System.out.println(Integer.toString(VS_COMPUTER_KEY) + ". Play against the computer");
		System.out.print("Please choose an option:");
	}
	
	private void chooseGameType() {
		int choice = QUIT_KEY;
		System.out.println("Welcome to Four in a Line!");
		printInitMenu();
		try {
			choice = Integer.parseInt(_terminalInput.nextLine());
			System.out.println();
		    while(choice != QUIT_KEY && choice != VS_HUMAN_KEY && choice != VS_COMPUTER_KEY)
		    {
				 System.out.println("Input incorrect! Please try again.");
				 printInitMenu();
				 choice = Integer.parseInt(_terminalInput.nextLine());
		    }
		}
		catch (NumberFormatException nfe) {
			System.out.println("Illegal choice");
		}
		
		
		_players.add(0, new HumanStrategy());
		if (choice == VS_HUMAN_KEY){
			_players.add(0, new HumanStrategy());
		}
		else if (choice == VS_COMPUTER_KEY){
			_players.add(1, new SimpleComputerStrategy());
		}
		else if (choice == QUIT_KEY){
			System.out.println("Bye bye!");
			_terminalInput.close();
			System.exit(0); //TODO: organized exit
		}
	}
	
	
	public void gameLoop() {
		chooseGameType();
		initViews();
		FourInARowModel.GameStatus gameStatus = FourInARowModel.GameStatus.CONTINUE;
		int currentPlayer = 0;
		int col;
		
		do {
			_players.get(currentPlayer).printMoveMessage(currentPlayer + 1);
			try {
				col = _players.get(currentPlayer % NUM_OF_PLAYERS).makeMove(_model);
				gameStatus = _model.addDisc(col, currentPlayer + 1);
				currentPlayer ++;
				currentPlayer %= NUM_OF_PLAYERS;
			}
			catch(ColumnFullException cfe) {
				System.out.println("Column is full");
			}
			catch(ColumnOutOfRangeException coore) {
				System.out.println("Column out of range");
			}
			catch(NumberFormatException nfe) { //makemove by human
				System.out.println("illegal column format");
			}
		} while (gameStatus == FourInARowModel.GameStatus.CONTINUE);
		
		if (gameStatus == FourInARowModel.GameStatus.WIN){
			_players.get((currentPlayer + 1) % NUM_OF_PLAYERS).printWinMessage((currentPlayer + 1) % NUM_OF_PLAYERS + 1);
		}
		else if (gameStatus == FourInARowModel.GameStatus.DRAW){ //TODO erase
			System.out.print("Board is full! game has ended with a tie!");
		}
		
	}
	
	protected void initViews() {
		for(int i = 0; i < _views.size(); i++) {
			_views.get(i).update(_model, _model.getBoard());
		}
	}
	
	
	public void addView(FourInARowView view) {
		_views.add(view);
		_model.addObserver(view);
		
	}
	
	public void removeView(FourInARowView view) {
		_views.remove(view);
		_model.deleteObserver(view);
	}

}
