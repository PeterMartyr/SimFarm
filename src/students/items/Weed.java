package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public class Weed extends Item
{
	private static final int AGE_HARVEST = Integer.MIN_VALUE;
	private static final int AGE_DEATH = Integer.MAX_VALUE;
	private static final int VALUE_HARVEST = -1;
	
	public static final String IS_MATURE = "#";
	
	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - when the item is ready to Harvest
	 * @param deathAge      - when the item dies
	 * @param monetaryValue - is the return price for successfully harvesting the crop
	 */
	public Weed(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}
	
	/**
	 * the No Args Constructor with default values
	 */
	public Weed() {
		this(Weed.AGE_HARVEST, Weed.AGE_DEATH, Weed.VALUE_HARVEST);
	}
	
	/**
	 * Copy constructor
	 * @param other object of this class
	 */
	private Weed(Item other) {
		super(other.maturationAge, other.deathAge, other.monetaryValue);
	}
	
	/**
	 * Returns a clone of this object
	 */
	@Override
	public Item clone() {
		return new Weed(this);
	}
	/**
	 * Returns representation of the item in a String
	 */
	@Override
	public String toString() {
		return Weed.IS_MATURE;
	}
}
