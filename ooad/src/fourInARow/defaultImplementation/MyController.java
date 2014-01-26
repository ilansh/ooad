package fourInARow.defaultImplementation;

import java.util.ArrayList;
import java.util.Scanner;

import fourInARow.controller.AController;
import fourInARow.excpetion.*;
import fourInARow.model.*;
import fourInARow.view.AbstractDiscFactory;
import fourInARow.view.CompositeGraphic;
import fourInARow.view.DiscFactory;
import fourInARow.view.IGameGraphic;
import fourInARow.view.View;

public class MyController extends AController{

	private static final int QUIT_KEY = 0;
	private static final int VS_HUMAN_KEY = 1;
	private static final int VS_COMPUTER_KEY = 2;
	private static final int ADD_VIEW_KEY = 3;
	private static final int REMOVE_VIEW_KEY = 4;
	private static final int DECORATE_BOARD_KEY = 5;
	private static final int DECORATE_DISC_KEY = 6;

	private Scanner _terminalInput;
	
	private ArrayList<CompositeGraphic> _boards;
	private ArrayList<IGameGraphic> _1discs;
	private ArrayList<IGameGraphic> _2discs;

	public MyController(IModel model) throws NullArgumentNotPermittedException {
		super(model);
		_terminalInput = new Scanner(System.in);
		_boards = new ArrayList<CompositeGraphic>();
		_1discs = new ArrayList<IGameGraphic>();
		_2discs = new ArrayList<IGameGraphic>();
		initView1();
	}

	
	private void initView1() {
		_1discs.add(new DiscGraphic('x'));
		_2discs.add(new DiscGraphic('o'));

		AbstractDiscFactory adf = AbstractDiscFactory.newInsance();
		DiscFactory df = null;
		try {
			df = adf.getFactoryImpl(_1discs.get(0), _2discs.get(0));
		} catch (NullArgumentNotPermittedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_boards.add(new BoardGraphic(df));

		try {
			addView(new View(_boards.get(0)));
		} catch (NullArgumentNotPermittedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initView2() {
		_1discs.add(new DiscGraphic('1'));
		_2discs.add(new DiscGraphic('2'));
		AbstractDiscFactory adf = AbstractDiscFactory.newInsance();
		DiscFactory df = null;
		try {
			df = adf.getFactoryImpl(_1discs.get(1), _2discs.get(1));
		} catch (NullArgumentNotPermittedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_boards.add(new BoardGraphic(df));


		try {
			addView(new View(_boards.get(1)));
		} catch (NullArgumentNotPermittedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// the main menu
	protected void printInitMenu() {
		System.out.println(Integer.toString(QUIT_KEY) + ". Exit");
		System.out.println(Integer.toString(VS_HUMAN_KEY)
				+ ". Play against a friend");
		System.out.println(Integer.toString(VS_COMPUTER_KEY)
				+ ". Play against the computer");
		System.out.println(Integer.toString(ADD_VIEW_KEY)
				+ ". Add a view to the game - SAMPLE IMPLEMENTATION! ADDS A VIEW WITH 1/2 DISC GRAPHICS");
		System.out.println(Integer.toString(REMOVE_VIEW_KEY)
				+ ". Remove a view from the game - SAMPLE IMPLEMENTATION! REMOVES FIRST VIEW");
		System.out.println(Integer.toString(DECORATE_BOARD_KEY)
				+ ". Decorate the board - SAMPLE IMPLEMENTATION! DECORATES BOARD IN FIRST VIEW WITH _____ ON TOP");
		System.out.println(Integer.toString(DECORATE_DISC_KEY)
				+ ". Decorate the disc - SAMPLE IMPLEMENTATION! DECORATES THE DISC WITH '*', THIS MESSES UP THE BOARD A LITTLE");
		
		System.out.print("Please choose an option:");
	}
	
	@Override
	protected boolean mainMenu() throws NullArgumentNotPermittedException,
			TooManyPlayersException {
//		if (_gameStatus != GameStatus.NOT_INIT) { // game is inited only after
//													// first move
//			// TODO: Throw exception
//		}
		boolean ret = false;
		int choice = QUIT_KEY;
		System.out.println("Welcome to Four in a Line!");
		printInitMenu();
		try {
			choice = Integer.parseInt(_terminalInput.nextLine());
			System.out.println();
			while (choice < QUIT_KEY && choice > DECORATE_DISC_KEY) {
				System.out.println("Input incorrect! Please try again.");
				printInitMenu();
				choice = Integer.parseInt(_terminalInput.nextLine());
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Illegal choice");
		}

		if (choice == VS_HUMAN_KEY) {
			addPlayer(new HumanStrategy(), "Player 1");
			addPlayer(new HumanStrategy(), "Player 2");
			ret = true;
		} else if (choice == VS_COMPUTER_KEY) {
			addPlayer(new HumanStrategy(), "Player 1");
			addPlayer(new SimpleComputerStrategy(), "Computer");
			ret = true;
		} 
		else if (choice == ADD_VIEW_KEY) {
			initView2();
		}
		else if (choice == REMOVE_VIEW_KEY) {
			removeView(_views.get(0));
		}
		else if (choice == DECORATE_BOARD_KEY) {
			CompositeGraphic b = new BorderBoard();
			_views.get(0).decorate(null, b, _boards.get(0));
		}
		else if (choice == DECORATE_DISC_KEY) {
			CompositeGraphic d1 = new DiscDecorator();
			CompositeGraphic d2 = new DiscDecorator();
			_views.get(0).decorate(_boards.get(0), d1, _1discs.get(0));
			_views.get(0).decorate(_boards.get(0), d2, _2discs.get(0));
		}
		else if (choice == QUIT_KEY) {
			System.out.println("Bye bye!");
			_terminalInput.close();
			System.exit(0); // TODO: organized exit
		}
		return ret;

	}

}
