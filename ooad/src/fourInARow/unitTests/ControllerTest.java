package fourInARow.unitTests;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

import fourInARow.aspects.GameLogger;
import fourInARow.controller.AController;
import fourInARow.defaultImplementation.*;
import fourInARow.excpetion.*;
import fourInARow.model.GameStatus;
import fourInARow.model.IModel;
import fourInARow.model.MyModel;
import fourInARow.player.IPlayer;
import fourInARow.player.Player;
import fourInARow.view.AbstractDiscFactory;
import fourInARow.view.CompositeGraphic;
import fourInARow.view.DiscFactory;
import fourInARow.view.IGameGraphic;
import fourInARow.view.View;

public class ControllerTest {

	private TestController _testController;
	// private AController _controller;
	private View _view;
	private IModel _model;
	private static PrintWriter _testWriter;
	private IPlayer _p1;
	private IPlayer _p2;

	class TestController extends AController {

		protected TestController(IModel model, IPlayer p1, IPlayer p2)
				throws NullArgumentNotPermittedException {
			super(model);
			_player1 = new Player(new SimpleComputerStrategy(), "udi", 1);
			_player2 = new Player(new SimpleComputerStrategy(), "mudi2", 2);
			_currentPlayer = _player1;
		}

		protected TestController(IModel model)
				throws NullArgumentNotPermittedException {
			super(model);

		}

		protected void setGameStatus(GameStatus status) {
			_gameStatus = status;
		}

		protected IPlayer getPlayer1() {
			return _player1;
		}

		protected IPlayer getPlayer2() {
			return _player2;
		}

		@Override
		protected boolean mainMenu() throws Exception {
			System.out.println("Test menu");
			return true;
		}
		
		protected void changeCurrentPlayer() {
			_currentPlayer = _player2;
		}

	}

	private void initView() {
		IGameGraphic disc1 = new DiscGraphic('x');
		IGameGraphic disc2 = new DiscGraphic('o');

		AbstractDiscFactory adf = AbstractDiscFactory.newInsance();
		DiscFactory df = null;
		try {
			df = adf.getFactoryImpl(disc1, disc2);
		} catch (NullArgumentNotPermittedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CompositeGraphic board = new BoardGraphic(df);

		_view = new View(board);

	}

	@Before
	public void setUp() {
		_model = new MyModel(5, 6);
		initView();
		try {
			// _controller = new MyController(_model);
			_p1 = new Player(new SimpleComputerStrategy(), "udi", 1);
			_p2 = new Player(new SimpleComputerStrategy(), "mudi2", 2);
			_testController = new TestController(_model, _p1, _p2);

		} catch (NullArgumentNotPermittedException e) {
			// TODO Auto-generated catch block
			fail("Can't instanciate controller");
		}
		try {
			_testWriter = new PrintWriter("testGame.log");
			GameLogger.initLogStream(_testWriter);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		_testWriter.close();
	}

	@Test(expected = NullArgumentNotPermittedException.class)
	public void testConstructorNullModel()
			throws NullArgumentNotPermittedException {
		new MyController(null);
	}

	@Test
	public void testGetGameStatusNotInit() {
		assertEquals("Game status should be NOT_INIT", GameStatus.NOT_INIT,
				_testController.getGameStatus());
	}

	@Test(expected = NullArgumentNotPermittedException.class)
	public void testAddViewNullView() throws NullArgumentNotPermittedException {
		_testController.addView(null);
	}

	@Test
	public void testAddViewValid() throws NullArgumentNotPermittedException {
		_testController.addView(_view);
		assertEquals("Model should have one observers", 1, _model
				.getObservable().countObservers());
	}

	@Test
	public void testRemoveViewValid() throws NullArgumentNotPermittedException {
		_testController.addView(_view);
		_testController.removeView(_view);
		assertEquals("Model should have no observers", 0, _model
				.getObservable().countObservers());
	}

	@Test(expected = NullArgumentNotPermittedException.class)
	public void testRemoveViewNullView()
			throws NullArgumentNotPermittedException {
		_testController.removeView(null);
	}

	@Test
	public void testPrintEndMessageNotInit() throws ColumnFullException,
			ColumnOutOfRangeException {
		_testController.printEndMessage();
	}

	@Test
	public void testPrintEndMessageOnGoing()
			throws NullArgumentNotPermittedException, TooManyPlayersException,
			NoViewsConfiguredException, NotEnoughPlayersException, Exception {
		_testController.setGameStatus(GameStatus.ONGOING);
		_testController.printEndMessage();
	}

	@Test
	public void testPrintEndMessageWin()
			throws NullArgumentNotPermittedException, TooManyPlayersException,
			NoViewsConfiguredException, NotEnoughPlayersException, Exception {
		_testController.setGameStatus(GameStatus.WIN);
		_testController.printEndMessage();
	}

	@Test
	public void testPrintEndMessageDraw()
			throws NullArgumentNotPermittedException, TooManyPlayersException,
			NoViewsConfiguredException, NotEnoughPlayersException, Exception {
		_testController.setGameStatus(GameStatus.DRAW);
		_testController.printEndMessage();
	}

	@Test
	public void testAddPlayer1() throws NullArgumentNotPermittedException,
			TooManyPlayersException {
		TestController tc = new TestController(_model);
		tc.addPlayer(new SimpleComputerStrategy(), "bla");
		assertNotNull(tc.getPlayer1());
	}

	@Test
	public void testAddPlayer2() throws NullArgumentNotPermittedException,
			TooManyPlayersException {
		TestController tc = new TestController(_model);
		tc.addPlayer(new SimpleComputerStrategy(), "bla1");
		tc.addPlayer(new SimpleComputerStrategy(), "bla2s");
		assertNotNull(tc.getPlayer2());
	}

	@Test(expected = TooManyPlayersException.class)
	public void testAddTooManyPlayers()
			throws NullArgumentNotPermittedException, TooManyPlayersException {
		TestController tc = new TestController(_model);
		tc.addPlayer(new SimpleComputerStrategy(), "bla1");
		tc.addPlayer(new SimpleComputerStrategy(), "bla2s");
		tc.addPlayer(new SimpleComputerStrategy(), "bla3");
	}

	@Test(expected = NoViewsConfiguredException.class)
	public void testInitGameNoViews() throws NullArgumentNotPermittedException,
			TooManyPlayersException, NoViewsConfiguredException,
			NotEnoughPlayersException, Exception {
		_testController.initGame();

	}
	
	@Test(expected = NotEnoughPlayersException.class)
	public void testInitGamePlayer1Null() throws NullArgumentNotPermittedException,
			TooManyPlayersException, NoViewsConfiguredException,
			NotEnoughPlayersException, Exception {
		TestController tc = new TestController(_model);
		tc.addView(_view);
		tc.initGame();
	}
	
	@Test(expected = NotEnoughPlayersException.class)
	public void testInitGamePlayer2Null() throws NullArgumentNotPermittedException,
			TooManyPlayersException, NoViewsConfiguredException,
			NotEnoughPlayersException, Exception {
		TestController tc = new TestController(_model);
		tc.addView(_view);
		tc.addPlayer(new SimpleComputerStrategy(), "ka");
		tc.initGame();
	}
	
	@Test
	public void testInitGameValid() throws NullArgumentNotPermittedException,
			TooManyPlayersException, NoViewsConfiguredException,
			NotEnoughPlayersException, Exception {
		_testController.addView(_view);
		_testController.initGame();
		assertEquals("Game Status should be ONGOING", GameStatus.ONGOING, _testController.getGameStatus());
	}

	@Test (expected = GameNotOngoingException.class)
	public void testPlayTurnGameNotOnGoing() throws NumberFormatException, ColumnFullException, ColumnOutOfRangeException, NullArgumentNotPermittedException, GameNotOngoingException {
		_testController.playTurn();
	}
	
	@Test 
	public void testPlayTurnValidPlayer1() throws NumberFormatException, ColumnFullException, ColumnOutOfRangeException, NullArgumentNotPermittedException, GameNotOngoingException {
		_testController.setGameStatus(GameStatus.ONGOING);
		_testController.playTurn();
		assertEquals("Game status should be ONGOING", GameStatus.ONGOING, _testController.getGameStatus());
	}
	
	@Test 
	public void testPlayTurnValidPlayer2() throws NumberFormatException, ColumnFullException, ColumnOutOfRangeException, NullArgumentNotPermittedException, GameNotOngoingException {
		_testController.setGameStatus(GameStatus.ONGOING);
		_testController.changeCurrentPlayer();
		_testController.playTurn();
		assertEquals("Game status should be ONGOING", GameStatus.ONGOING, _testController.getGameStatus());
	}

}
