package fourInARow.defaultImplementation;

import java.util.Scanner;

import fourInARow.controller.AController;
import fourInARow.excpetion.*;
import fourInARow.model.*;

public class MyController extends AController {

	private static final int QUIT_KEY = 0;
	private static final int VS_HUMAN_KEY = 1;
	private static final int VS_COMPUTER_KEY = 2; // TODO: char or int

	private Scanner _terminalInput;

	public MyController(MyModel model) {
		super(model);
		_terminalInput = new Scanner(System.in);
	}

	// the main menu
	protected void printInitMenu() {
		System.out.println(Integer.toString(QUIT_KEY) + ". Exit");
		System.out.println(Integer.toString(VS_HUMAN_KEY)
				+ ". Play against a friend");
		System.out.println(Integer.toString(VS_COMPUTER_KEY)
				+ ". Play against the computer");
		System.out.print("Please choose an option:");
	}

	@Override
	protected void mainMenu() throws NullArgumentNotPermittedException,
			TooManyPlayersEception {
		if (_gameStatus != GameStatus.NOT_INIT) { // game is inited only after
													// first move
			// TODO: Throw exception
		}
		int choice = QUIT_KEY;
		System.out.println("Welcome to Four in a Line!");
		printInitMenu();
		try {
			choice = Integer.parseInt(_terminalInput.nextLine());
			System.out.println();
			while (choice != QUIT_KEY && choice != VS_HUMAN_KEY
					&& choice != VS_COMPUTER_KEY) {
				System.out.println("Input incorrect! Please try again.");
				printInitMenu();
				choice = Integer.parseInt(_terminalInput.nextLine());
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Illegal choice");
		}

		addPlayer(new HumanStrategy(), "Player 1");

		if (choice == VS_HUMAN_KEY) {
			addPlayer(new HumanStrategy(), "Player 2");
		} else if (choice == VS_COMPUTER_KEY) {
			addPlayer(new SimpleComputerStrategy(), "Computer");
		} else if (choice == QUIT_KEY) {
			System.out.println("Bye bye!");
			_terminalInput.close();
			System.exit(0); // TODO: organized exit
		}

	}

}
