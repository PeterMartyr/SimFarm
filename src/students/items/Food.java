package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public abstract class Food extends Item {

	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - when the item is ready to Harvest
	 * @param deathAge      - when the item dies
	 * @param monetaryValue - is the return price for successfully harvesting the
	 *                      crop
	 */
	protected Food(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}

	/**
	 * 
	 * @return true if the Food is ripe and ready for harvest false if not
	 */
	public boolean isMature() {
		return age < maturationAge;
	}
}
