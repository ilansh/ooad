//
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import fourInARow.controller.AController;
//import fourInARow.controller.IController;
//import fourInARow.excpetion.*;
//import fourInARow.model.*;
//import fourInARow.player.*;
//import fourInARow.view.*;
//
//public class MyController extends AController{
//	
//	
//	
//	
//	
//	
//
//
//
//	// the main menu
//	private static final int QUIT_KEY = 0;
//	private static final int VS_HUMAN_KEY = 1;
//	private static final int VS_COMPUTER_KEY = 2; //TODO: char or int
//	
////	private Scanner _terminalInput;
////	
////	
////	public MyController(MyModel model) {
////		super(model);
////	}
////	public MyController(MyModel model){
////	
////	}
////		
//
////		WindowGraphic window = new WindowGraphic(); //TODO" remove this
////		window.addGraphic(board);
//		
////		BorderBoard b = new BorderBoard();
//		
////		View view = new View(board);
////		View view = new View(window);
////		view.decorate(null, b, window);
////		addView(view);
//		
//	
//	
////	public MyGame(FourInARowModel model, FourInARowView view){
////		_model = model;
////		_views = new ArrayList<FourInARowView>();
////		_terminalInput = new Scanner(System.in);
////		_gameStatus = GameStatus.NOT_INIT;
////		addView(view);
////		_players = new ArrayList<Player>(NUM_OF_PLAYERS);
////		_currentPlayer = 0;
////
////	}
////	
////	public MyGame(FourInARowModel model, ArrayList<FourInARowView> views){
////		_model = model;
////		_views = new ArrayList<FourInARowView>();
////		_terminalInput = new Scanner(System.in);
////		_gameStatus = GameStatus.NOT_INIT;
////		_currentPlayer = 0;
////		_players = new ArrayList<Player>(NUM_OF_PLAYERS);
////		for(int i = 0; i < views.size(); i++) {
////			addView(views.get(i));
////		}
////	}
//
//	protected void printInitMenu() {
//		System.out.println(Integer.toString(QUIT_KEY) + ". Exit");
//		System.out.println(Integer.toString(VS_HUMAN_KEY) + ". Play against a friend");
//		System.out.println(Integer.toString(VS_COMPUTER_KEY) + ". Play against the computer");
//		System.out.print("Please choose an option:");
//	}
//
//
////@Override
////public void mainMenu() {
////	// TODO Auto-generated method stub
////	
////}
//	
//
//	
//
//	
//	/*public void initViews() {
//		for(int i = 0; i < _views.size(); i++) {
//			_views.get(i).update(_model, _model.getBoard());
//		}
//	}
//	
//	
//	public void addView(View view) {
//		if(view == null) {
//			return;
//			//TODO" throw exception
//		}
//		_views.add(view);
//		_model.addObserver(view);
//		
//	}
//	
//	public void removeView(View view) {
//		if(view == null) {
//			//TODO" throw exception
//		}
//		_views.remove(view);
//		_model.deleteObserver(view);
//	}
//
//
//	@Override
//	public GameStatus playTurn() throws ColumnFullException, ColumnOutOfRangeException, NumberFormatException {
//		if(_gameStatus != GameStatus.ONGOING) {
//			//TODO: Throw Exception
//		}
//		int col;
//		_players.get(_currentPlayer).printMoveMessage();
//		col = _players.get(_currentPlayer % NUM_OF_PLAYERS).move(_model);
//		_gameStatus = _model.addDisc(col, _currentPlayer + 1);
//		if(_gameStatus == GameStatus.ONGOING)
//		{
//			_currentPlayer ++;
//			_currentPlayer %= NUM_OF_PLAYERS;
//		}
//		return _gameStatus;
//	}
//
//	
//
//	@Override
//	public void mainMenu() {
//		if(_gameStatus != GameStatus.NOT_INIT) { //game is inited only after first move
//			//TODO: Throw exception		
//		}
//		int choice = QUIT_KEY;
//		System.out.println("Welcome to Four in a Line!");
//		printInitMenu();
//		try {
//			choice = Integer.parseInt(_terminalInput.nextLine());
//			System.out.println();
//		    while(choice != QUIT_KEY && choice != VS_HUMAN_KEY && choice != VS_COMPUTER_KEY)
//		    {
//				 System.out.println("Input incorrect! Please try again.");
//				 printInitMenu();
//				 choice = Integer.parseInt(_terminalInput.nextLine());
//		    }
//		}
//		catch (NumberFormatException nfe) {
//			System.out.println("Illegal choice");
//		}
//		
//		_players.add(new Player(new HumanStrategy(), "Player 1", 1));
//		if (choice == VS_HUMAN_KEY){
//			_players.add(new Player(new HumanStrategy(), "Player 2", 2));
//		}
//		else if (choice == VS_COMPUTER_KEY){
//			_players.add(new Player(new SimpleComputerStrategy(), "Computer", 2));
//		}
//		else if (choice == QUIT_KEY){
//			System.out.println("Bye bye!");
//			_terminalInput.close();
//			System.exit(0); //TODO: organized exit
//		}
//		
//		_gameStatus = GameStatus.ONGOING;
//	}
//
//	@Override
//	public GameStatus getGameStatus(){
//		return _gameStatus;
//	}
//	
//	public void printEndMessage() {
//		if(_gameStatus == GameStatus.WIN) {
//			_players.get(_currentPlayer).printWinMessage();
//		}
//		else if(_gameStatus == GameStatus.DRAW){
//			System.out.print("Board is full! game has ended with a tie!");
//		}
//		//TODO: throw game not over exception
//	}*/
//
//}
