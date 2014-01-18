/**
 * 
 */
package UnitTests;

import GameLibrary.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import Exceptions.IllegalValueException;

/**
 * Unit tests for the Board class methods.
 * @author Ehud Doron (udid), Ortal Azoulay (ortalit)
 *
 */
public class BoardTest {
	
	
	/** Constructor tests */
	
	@Test
	public void BoardDefaultConstructor_NoChange_EmptyBoard() {
		Board b = new Board();
		assertEquals("isBoardFull() should return false", false, b.isBoardFull());
		assertEquals("isColumnFull(0) should return false", false, b.isColumnFull(0));
		try {
			assertEquals("isWinningMove(0,0,1) should return false", 
											false, b.isWinningMove(0, 0, (byte) 1));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by b.isWinningMove() for no reason");
		}
	}
	
	
	@Test
	public void BoardParameteredConstructors_OkValues_EmptyBoard() {
		Board b;
		try {
			b = new Board(7, 7, 5);
			assertEquals("isBoardFull() should return false", false, b.isBoardFull());
			assertEquals("isColumnFull(0) should return false", false, b.isColumnFull(0));
			assertEquals("isWinningMove(0,0,1) should return false", 
										false, b.isWinningMove(0, 0, (byte) 1));
		} catch (IllegalValueException e) {
			fail("IllegalValueException was thrown by the board parametered constructor or b.isWinningMove()");
		}
	}
	
	@Test(expected = IllegalValueException.class)
	public void BoardParameteredConstructor_BadHeight_IllegalValueException() throws IllegalValueException {
		Board b = new Board(-1, 2, 2);
		b.resetBoard(); //To disable the warning after not using 'b'. Totally dismissable.
	}
	
	
	/** Setters and getters tests */
	
	
	@Test
	public void getBoard_regularBoard() {
		Board b = new Board();
		byte[][] brd = b.getBoard();
		assertEquals("Should receive a byte[][] of default height, i.e. 6", 
					Board.DEFAULT_BOARD_HEIGHT, brd.length);
		assertEquals("Should receive a byte[][] of default width, i.e. 7", 
					Board.DEFAULT_BOARD_WIDTH, brd[0].length);
	}
	
	
	@Test
	public void getBoardHeight_DefaultBoard_DefaultBoardHeight() {
		Board b = new Board();
		int height = b.getBoardHeight();
		assertEquals("(defaultBoard).getBoardHeight() should return default board height",
					Board.DEFAULT_BOARD_HEIGHT, height);
	}
	
	
	
	@Test
	public void getBoardHeight_CustomBoard_CustomHeight() {
		try {
			Board b = new Board(3, 3, 2);
			int height = b.getBoardHeight();
			assertEquals("b.getBoardHeight() should return 3", 3, height);
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void getBoardWidth_DefaultBoard_DefaultBoardWidth() {
		Board b = new Board();
		int width = b.getBoardWidth();
		assertEquals("(defaultBoard).getBoardWidth() should return default board width",
					Board.DEFAULT_BOARD_WIDTH, width);
	}
	
	
	
	@Test
	public void getBoardWidth_CustomBoard_CustomWidth() {
		try {
			Board b = new Board(3, 3, 2);
			int width = b.getBoardWidth();
			assertEquals("b.getBoardWidth() should return 3", 3, width);
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void getWinningNum_DefaultBoard_DefaultWinningNum() {
		Board b = new Board();
		int winningNum = b.getWinningNum();
		assertEquals("(defaultBoard).getWinningNum() should return default winning num",
					Board.DEFAULT_WINNING_NUM, winningNum);
	}
	
	
	
	@Test
	public void getWinningNum_CustomBoard_CustomWinningNum() {
		try {
			Board b = new Board(3, 3, 2);
			int winningNum = b.getWinningNum();
			assertEquals("b.getWinningNum should return 2", 2, winningNum);
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void getLastPlayer_startOfGame_Player2() {
		Board b = new Board();
		byte last = b.getLastPlayer();
		assertEquals("b.getLastPlayer() should return 2",(byte) 2, last);
	}
	
	
	@Test
	public void getLastCol_startOfGame_MinusOne() {
		Board b = new Board();
		int last = b.getLastCol();
		assertEquals("b.getLastCol() should return -1", -1, last);
	}
	
	
	@Test
	public void getLastRow_startOfGame_MinusOne() {
		Board b = new Board();
		int last = b.getLastRow();
		assertEquals("b.getLastPlayer() should return -1",-1 , last);
	}
	
	
	@Test
	public void hasWinner_startOfGame_False() {
		Board b = new Board();
		assertEquals("b.hasWinner() should return false", false, b.hasWinner());
	}
	
	
	@Test
	public void getWinner_startOfGame_Zero() {
		Board b = new Board();
		byte winner = b.getWinner();
		assertEquals("b.getLastPlayer() should return 0",(byte) 0, winner);
	}
	
	
	@Test
	public void setLastPlayer_CorrectInput_InputIsSet() {
		Board b = new Board();
		b.setLastPlayer((byte) 1);
		assertEquals("b.getLastPlayer() should return 1", (byte) 1, b.getLastPlayer());
		b.setLastPlayer((byte) 2);
		assertEquals("b.getLastPlayer() should return 2", (byte) 2, b.getLastPlayer());
	}
	
	
	@Test
	public void setLastPlayer_BadInput_SameLastPlayer() {
		Board b = new Board();
		//setting initial last player to be 2.
		b.setLastPlayer((byte) 2);
		//setting incorrect last player.
		b.setLastPlayer((byte) 3);
		assertEquals("b.getLastPlayer() should return 2", (byte) 2, b.getLastPlayer());
		//setting another incorrect last player.
		b.setLastPlayer((byte) -1);
		assertEquals("b.getLastPlayer() should still return 2", (byte) 2, b.getLastPlayer());
	}
	
	
	/** General method tests (without getters and setters) */
	
	@Test
	public void resetBoard_EmptyBoard() {
		Board b = new Board();
		try {
			b.insertInto(0);
			b.resetBoard();
			assertEquals("getValue(board.getBoardHeight()-1, 0) should return 0", 
										(byte) 0, b.getValue(b.getBoardHeight()-1, 0));
			assertEquals("lastPlayer should be 2", (byte) 2, b.getLastPlayer());
			assertEquals("lastRow should be -1", -1, b.getLastRow());
			assertEquals("lastCol should be -1", -1, b.getLastCol());
			assertEquals("hasWinner should be false", false, b.hasWinner());
			assertEquals("winner should be Board.NO_WINNER", Board.NO_WINNER, b.getWinner());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by b.insertInto(0) for no reason");
		}
	}
	
	
	@Test
	public void isColumnFull_ColumnNotFull_False() {
		try {
			Board b = new Board(2, 2, 2);
			b.setLastPlayer((byte) 1);
			b.insertInto(0);
			assertEquals("isColumnFull(0) should return false", false, b.isColumnFull(0));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void isColumnFull_ColumnFull_True() {
		try {
			Board b = new Board(2,2,2);
			//filling the column with ones.
			b.setLastPlayer((byte) 1);
			b.insertInto(0);
			b.insertInto(0);
			assertEquals("isColumnFull(0) should return true", true, b.isColumnFull(0));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void isBoardFull_BoardNotFull_False() {
		try {
			Board b = new Board(2, 2, 2);
			b.setLastPlayer((byte) 1);
			b.insertInto(0);
			assertEquals("isBoardFull() should return false", false, b.isBoardFull());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void isBoardFull_BoardFull_True() {
		try {
			Board b = new Board(2,2,2);
			//filling the board with ones.
			b.setLastPlayer((byte) 1);
			b.insertInto(0);
			b.insertInto(0);
			b.insertInto(1);
			b.insertInto(1);
			assertEquals("isBoardFull() should return true", true, b.isBoardFull());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test(expected = IllegalValueException.class)
	public void setWinner_BadInput_IllegalValueException() throws IllegalValueException {
		Board b = new Board();
		b.setWinner((byte) -1);
	}
	
	
	@Test
	public void firstEmptyRow_ColumnNotFull_RegularRow() {
		Board b = new Board();
		b.setLastPlayer((byte) 1);
		try {
			b.insertInto(0);
			assertEquals("firstEmptyRow(0) should return board.height-2", 
					b.getBoardHeight()-2, b.firstEmptyRow(0));
			assertEquals("firstEmptyRow(1) should return board.height-1", 
					b.getBoardHeight()-1, b.firstEmptyRow(1));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by b.insertInto() or b.firstEmptyRow(), " +
																"for no reason.");
		}
	}
	
	
	@Test
	public void firstEmptyRow_ColumnFull_MinusOne() {
		try {
			Board b = new Board(2, 2, 2);
			//filling the 0 column.
			b.insertInto(0);
			b.insertInto(0);
			assertEquals("firstEmptyRow(0) should return -1", -1, b.firstEmptyRow(0));
			assertEquals("firstEmptyRow(1) should return board.height-1", b.getBoardHeight()-1, b.firstEmptyRow(1));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void isWinningMove_NotWinning_False() {
		try {
			Board b = new Board(3,3,3);
			b.setLastPlayer((byte) 1);
			b.insertInto(0);
			assertEquals("isWinningMove(1, 0, 1) should return false",
					false, b.isWinningMove(b.firstEmptyRow(0), 0, (byte) 1));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void isWinningMove_WinningMove_True() {
		try {
			Board b = new Board(2, 3, 2);
			b.setLastPlayer((byte) 1);
			b.insertInto(0);
			assertEquals("isWinningMove(0, 0, 1) should return true", 
						true, b.isWinningMove(b.firstEmptyRow(0), 0, (byte) 1));
			assertEquals("isWinningMove(1, 2, 1) should return false", 
						false, b.isWinningMove(b.firstEmptyRow(2), 2, (byte) 1));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test
	public void insertInto_OkValues_True() {
		Board b = new Board();
		b.setLastPlayer((byte) 1);
		boolean result;
		try {
			result = b.insertInto(0);
			assertEquals("b.getValue(b.getBoardHeight()-1, 0) should return 1", 
					(byte) 1, b.getValue(b.getBoardHeight()-1, 0));
			assertEquals("insertInto should've returned true", true, result);
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by b.insertInto().");
		}
	}
	
	
	@Test
	public void insertInto_FullColumn_False() {
		try {
			Board b = new Board(2, 2, 2);
			b.setLastPlayer((byte) 1);
			//filling the column
			b.insertInto(0);
			b.insertInto(0);
			//checking the result
			boolean result = b.insertInto(0);
			assertEquals("Third insertInto(0) should returned false", false, result);
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by the board constructor.");
		}
	}
	
	
	@Test(expected = IllegalValueException.class)
	public void insertInto_IllegalInput_IllegalValueException() throws IllegalValueException {
		Board b = new Board(2, 2, 2);
		b.setLastPlayer((byte) 1);
		b.insertInto(-1); //should throw an exception
		b.insertInto(b.getBoardWidth());
		//assertEquals("insertInto(-1) should return false", false, firstResult);
		//assertEquals("insertInto(b.getBoardWidth()) should return false", false, secondResult);
		
	}
	
	
	@Test
	public void getValue_goodInput_RegularResult() {
		Board b = new Board();
		b.setLastPlayer((byte) 1);
		try {
			b.insertInto(0);
			assertEquals("b.getValue(b.getBoardHeight()-1, 0) should return 1", 
					(byte) 1, b.getValue(b.getBoardHeight()-1, 0));
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by b.insertInto().");
		}
	}
	
	
	@Test
	public void getValue_badInput_MinusOne() {
		Board b = new Board();
		byte firstResult = b.getValue(-1, 0);
		byte secondResult = b.getValue(0, b.getBoardWidth());
		assertEquals("b.getValue(-1,0) should return -1", (byte) -1, firstResult);
		assertEquals("b.getValue(0, b.getBoardWidth() should return -1", (byte) -1, secondResult);
	}
	
	
	@Test
	public void toString_startGame_EverythingOK() {
		Board b = new Board();
		System.out.println(b.toString());
	}
	
	
	@Test
	public void toString_moveMade_EverythingOK() {
		Board b = new Board();
		try {
			b.insertInto(0);
			System.out.println(b.toString());
		} catch (IllegalValueException e) {
			fail("An IllegalValueException was thrown by b.insertInto().");
		}
	}
	

	@Ignore("Template for tests")
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
