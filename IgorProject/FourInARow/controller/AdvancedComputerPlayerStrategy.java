

package FourInARow.controller;


import java.util.Random;
import java.util.Scanner;

import FourInARow.board.Board;
import FourInARow.board.BoardCell;
import FourInARow.model.FourInARowModel;
import FourInARow.model.ModelInterface;


public class AdvancedComputerPlayerStrategy extends ComputerPlayerStrategy{

	int _colSize;


	public AdvancedComputerPlayerStrategy(int colSize){
		super(colSize);
	}


	//default computerStrategy - picks random column 0 to colSize


	//igor
	@Override
	public int makeMove(ModelInterface model) throws NumberFormatException{

		FourInARowModel tempModel = new FourInARowModel(model);
		Board board = tempModel.getBoardObject();

		int emptyrow = 0;
		// first check if a move can win
		for (int i=0; i<board.getColSize(); i++) {
			if (!board.isColFull(i)) {
				emptyrow = board.firstEmptyRow(i);
				board.setCell(BoardCell.COLORTWO, i, emptyrow);//computer is always p2
				if (FourInARowModel.isWinnerDisk(tempModel, i, emptyrow)) {
					return i;
				}
			}
		}

		Random generator = new Random(); 
		int choice =  (this._colSize) - generator.nextInt(this._colSize);
		return choice;

	}


	@Override
	public void setScanner(Scanner sc) { }

}
