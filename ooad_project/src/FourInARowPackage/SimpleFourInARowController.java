package FourInARowPackage;

import java.util.ArrayList;

public class SimpleFourInARowController extends FourInARowController{
	
	public SimpleFourInARowController(FourInARowModel model, ArrayList<FourInARowView> views){
		_model = model;
		_views = views;
		_model.notifyObservers(_model.getBoard());
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void printInitMenu() {
		// TODO Auto-generated method stub
		
	}
	
	
}
