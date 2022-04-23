package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public abstract class Soil extends Item {
	
	protected static final int AGE_HARVEST = Integer.MIN_VALUE;
	protected static final int AGE_DEATH = Integer.MAX_VALUE;
	
	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - when the item is ready to Harvest
	 * @param deathAge      - when the item dies
	 * @param monetaryValue - is the return price for successfully harvesting the crop
	 */
	public Soil(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}
}
