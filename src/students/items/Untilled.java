package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public class Untilled extends Soil {

	private static final int VALUE_HARVEST = -1;

	private static final String IS_MATURE = "/";

	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - when the item is ready to Harvest
	 * @param deathAge      - when the item dies
	 * @param monetaryValue - is the return price for successfully harvesting the
	 *                      crop
	 */
	public Untilled(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}

	/**
	 * the No Args Constructor with default values
	 */
	public Untilled() {
		this(Soil.AGE_HARVEST, Soil.AGE_HARVEST, Untilled.VALUE_HARVEST);
	}

	/**
	 * Copy constructor
	 * 
	 * @param other object of this class
	 */
	private Untilled(Item other) {
		super(other.maturationAge, other.deathAge, other.monetaryValue);
	}

	/**
	 * a clone of this object
	 */
	@Override
	public Item clone() {
		return new Untilled(this);
	}

	/**
	 * Returns representation of each item in a String
	 */
	@Override
	public String toString() {
		return Untilled.IS_MATURE;
	}
}
