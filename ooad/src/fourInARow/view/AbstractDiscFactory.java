package fourInARow.view;

import java.util.HashMap;

/**
 * Variation on Abstract factory - we create disc factories dynamically
 * by getting a disc graphic implementation from the user and creating a
 * factory for that implementation.
 * All users implementing the same graphic interface will get the same discFactory.
 *
 */
public class AbstractDiscFactory {
	
	private HashMap <IGameGraphic, DiscFactory> _factoriesPool;
	
	private static AbstractDiscFactory _instance = null;
	
	private AbstractDiscFactory() {
		_factoriesPool = new HashMap <IGameGraphic, DiscFactory>();
	}
	
	public static AbstractDiscFactory newInsance() {
		if(_instance == null ) {
			_instance =  new AbstractDiscFactory();
		}
		return _instance;
	}
	
	public void addFactoryImpl(IGameGraphic disc) {
		if(!_factoriesPool.containsKey(disc)) {
			_factoriesPool.put(disc.clone(), new DiscFactory(disc.clone()));
		}
	}
	
	public DiscFactory getFactory(IGameGraphic discGraphic) {
		if (!_factoriesPool.containsKey(discGraphic)) {
			return null;
			//TODO maybe throw exception
		}
		return _factoriesPool.get(discGraphic);
	}
	
}
