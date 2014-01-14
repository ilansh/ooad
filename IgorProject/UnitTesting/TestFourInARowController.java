package UnitTesting;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


import FourInARow.controller.MyFourInARowController;
import FourInARow.model.FourInARowModel;
import FourInARow.model.GameState;
import FourInARow.view.BoardGraphics;
import FourInARow.view.BoardGraphicsPool;
import FourInARow.view.CellGraphics;
import FourInARow.view.CellGraphicsPool;
import FourInARow.view.CompositeGraphics;
import FourInARow.view.FourInARowView;
import FourInARow.view.ViewInterface;

public class TestFourInARowController {
	
	FourInARowModel myModel;
	CompositeGraphics gameGraphics;
	CellGraphics playerOne;
	CellGraphics playerTwo;
	CellGraphics empty;
	CellGraphics boarder;
	CellGraphics newline;
	BoardGraphics board;
	ArrayList<ViewInterface> views;
	ViewInterface view;
	MyFourInARowController controller;
	File initfile;
	File userinputfile;
	Scanner initsc;
	Scanner userinputsc1;
	Scanner userinputsc2;
	@Before
	public void setUp() throws Exception {
		
		myModel = new FourInARowModel(4,5,3);
		gameGraphics = new CompositeGraphics();
		CellGraphicsPool.initPool(); //use static factory to receive cell instances
		playerOne = CellGraphics.getCellInstance('x');
		playerTwo = CellGraphics.getCellInstance('o');
		empty = CellGraphics.getCellInstance(' ');
		boarder = CellGraphics.getCellInstance('|');
		newline = CellGraphics.getCellInstance('\n');
		BoardGraphicsPool.initPool();
		board = BoardGraphics.getBoardInstance(playerOne, playerTwo, empty, boarder, newline);
		gameGraphics.addGraphics(board);
		views = new ArrayList<ViewInterface>();
		view = new FourInARowView(gameGraphics);
		views.add(view);
		for (ViewInterface v : views){
			myModel.addLocalObserver(v);
		}
		controller = new MyFourInARowController(myModel, views);
		controller.addlocalObserver(myModel);
		initfile = new File("init.txt");
		userinputfile = new File("userInput.txt");
		initsc = new Scanner(initfile);
		userinputsc1 = new Scanner(userinputfile);
		userinputsc2 = new Scanner(userinputfile);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testFourInARowController() {
		FourInARowModel model = new FourInARowModel(6,5,4);
		ArrayList<ViewInterface> views = new ArrayList<ViewInterface>(); 
		MyFourInARowController controller = new MyFourInARowController(model, views);
		assertNotNull("Scanner was instantiated", controller.getScanner());
	}

	@Test
	public final void testStartGameOnePlayer() throws IOException {
		controller.setScanner(initsc);
		controller.startGame(userinputsc1, null);
		assertTrue("we have a winner", myModel.getGameState()==GameState.WON
					||myModel.getGameState()==GameState.DRAW);
	}
	@Test
	public final void testStartGameOTwoPlayer() throws IOException {
		controller.setScanner(initsc);
		controller.startGame(userinputsc1, userinputsc2);
		assertTrue("we have a winner", myModel.getGameState()==GameState.WON
					||myModel.getGameState()==GameState.DRAW);
		
	}

}
