package FourInARow.controller;

import java.util.Scanner;

import FourInARow.model.ModelInterface;

public interface ControllerInterface {
		
	public abstract void startGame(Scanner sc1, Scanner sc2);

	public abstract void addlocalObserver(ModelInterface myModel);
}
