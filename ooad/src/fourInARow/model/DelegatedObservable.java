package fourInARow.model;

import java.util.Observable;

public class DelegatedObservable extends Observable {
	
	public void setChanged() {
		super.setChanged();
	}
	
	public void clearChanged() {
		super.clearChanged();
	}
}
