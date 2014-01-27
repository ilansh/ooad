package fourInARow.view;

import java.util.HashMap;

import fourInARow.excpetion.NullArgumentNotPermittedException;

/**
 * Variation on Abstract factory - we create disc factories dynamically
 * by getting two disc graphic implementations from the user and creating a
 * flyweight factory for these implementations.
 * All users implementing the same graphic interface will get the same discFactory.
 *
 */
public class AbstractDiscFactory {
	
	private HashMap <IGameGraphic, DiscFactory> _factoriesPool;
	
	//The singleton instance
	private static AbstractDiscFactory _instance = null;
	
	private AbstractDiscFactory() {
		_factoriesPool = new HashMap <IGameGraphic, DiscFactory>();
	}
	
	/**
	 * Get the singleton instance of the abstract factory
	 * @return This object's instance
	 */
	public static AbstractDiscFactory newInsance() {
		if(_instance == null ) {
			_instance =  new AbstractDiscFactory();
		}
		return _instance;
	}
	
	/**
	 * Get a factory implementation for the given Graphics.
	 * The arguments should implement hashCode and equals correctly, as well as clone.
	 * @param disc1 - the disc1 graphic
	 * @param disc2 = the disc2 graphic
	 * @return A factory implementation containing disc1 and disc2
	 * @throws NullArgumentNotPermittedException
	 */
	public DiscFactory getFactoryImpl(IGameGraphic disc1, IGameGraphic disc2) throws NullArgumentNotPermittedException {
		if(disc1 == null || disc2 == null) {
			throw new NullArgumentNotPermittedException();
		}
		DiscFactory df = _factoriesPool.get(disc1);
		if(df == null) {
			//using clone to prevent the disc1 being changed from outside
			//since map keys should be immutable.
			//and the same goes for the discs in the factory. we don't want them to be changed
			df = new DiscFactory(disc1.clone(), disc2.clone());
			_factoriesPool.put(disc1.clone(), df);
		}
		return df;
	}
	
}
