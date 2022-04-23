package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public abstract class Grain extends Food {

	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - when the item is ready to Harvest
	 * @param deathAge      - when the item dies
	 * @param monetaryValue - is the return price for successfully harvesting the
	 *                      crop
	 */
	protected Grain(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}
}
