package FourInARow.controller;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Scanner;

//import FourInARow.model.FourInARowModel;
import FourInARow.model.GameState;
import FourInARow.model.ModelInterface;
//import FourInARow.view.FourInARowView;
import FourInARow.view.ViewInterface;



public class MyFourInARowController extends FourInARowController implements ControllerInterface {

	private Scanner _input;


	public MyFourInARowController(ModelInterface myModel, ArrayList<ViewInterface> views){
		super(myModel, views);
		_input = new Scanner(System.in);
	}


	private void printMenu(int col, int row) {

		System.out.println("Welcome To The Four In A Row Game!");
		System.out.println("Dimensions of the board are "+row+"*"+col);
		System.out.println("1. Play against a friend");
		System.out.println("2. Play against the computer");
		System.out.println("3. Exit");
		System.out.println("Please choose an option:");
	}

	private void printComputerLevel() {

		System.out.println("1. Easy");
		System.out.println("2. Advanced");
		System.out.println("Please Choose Computer Level:");
	}


	private void initGame(){

		int input = -1;
		printMenu(_model.getBoardObject().getColSize(), _model.getBoardObject().getRowSize());
		input = Integer.parseInt(_input.nextLine());

		while(input>3 || input<1)
		{
			System.out.println("Wrong input. Retry.");
			input = Integer.parseInt(_input.nextLine());
		}

		if (input == 1){//human opponent
			_playerOne = new HumanPlayerStrategy();
			_playerTwo = new HumanPlayerStrategy();

		}
		else if (input == 2){//computer opponent

			_playerOne = new HumanPlayerStrategy();
			printComputerLevel();
			input = Integer.parseInt(_input.nextLine());

			while(input<1 || input>2){
				System.out.println("Wrong input. Retry.");
				input = Integer.parseInt(_input.nextLine());
			}

			int colums = _model.getBoardObject().getColSize();
			if(input == 1){
				_playerTwo = new SimpleComputerPlayerStrategy(colums);
			}
			else{ //input == 2
				_playerTwo = new AdvancedComputerPlayerStrategy(colums);
			}
		}

		else if (input == 3){
			System.out.println("Your loose! GoodBye!");
			_input.close();
			System.exit(0); 
		}
	}




	public void startGame(Scanner sc1, Scanner sc2) {

		initModel();
		initGame();
		if (sc1 != null){
			_playerOne.setScanner(sc1);
		}
		if (sc2 != null){
			_playerOne.setScanner(sc2);
		}

		while(GameState.PROGRESS == _model.getGameState() || GameState.ILLEGAL == _model.getGameState()){

			int move;

			if(_model.getTurn() == 1){
				move = _playerOne.makeMove(_model);//aspectJ here
				System.out.println("Player 1 chose col: "+move);
			}
			else{
				move = _playerTwo.makeMove(_model);
				System.out.println("Player 2 chose col: "+move);
			}

			//notify the model to change state 
			setChanged();
			notifyObservers(move);

			if (GameState.ILLEGAL == _model.getGameState()){
				System.out.println("Player "+_model.getTurn()+" made an illegal move!");
				continue;
			}
		}

		if (GameState.WON == _model.getGameState()){
			System.out.println("Player "+_model.getTurn()+" won the game!");

		}
		else if (GameState.DRAW == _model.getGameState()){ 
			System.out.print("Draw! The board is full");
		}

	}


	@Override
	public void addlocalObserver(ModelInterface myModel) {
		this.addObserver((Observer) myModel);
	}


	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return _input;
	}

	public void setScanner(Scanner sc){
		_input = sc;
	}
}
