package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public class Barley extends Grain {

	private static final int AGE_HARVEST = 3;
	private static final int AGE_DEATH = 5;
	private static final int VALUE_HARVEST = 2;
	public static final int VALUE_BUY = 1;

	private static final String NOT_MATURE = "b";
	private static final String IS_MATURE = "B";

	private static int generationCount = 0;

	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - when the item is ready to Harvest
	 * @param deathAge      - when the item dies
	 * @param monetaryValue - is the return price for successfully harvesting the
	 *                      crop
	 */
	public Barley(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
		Barley.generationCount++;
	}

	/**
	 * the No Args Constructor with default values
	 */
	public Barley() {
		this(Barley.AGE_HARVEST, Barley.AGE_DEATH, Barley.VALUE_HARVEST);
	}

	/**
	 * Copy constructor
	 * 
	 * @param other object of this class
	 */
	private Barley(Barley other) {
		super(other.maturationAge, other.deathAge, other.monetaryValue);
		this.age = other.age;
	}

	/**
	 * return a clone of this object
	 */
	@Override
	public Barley clone() {
		return new Barley(this);
	}

	/**
	 * 
	 * @return Return a static count of all object created
	 */
	public static int getGenerationCount() {
		return Barley.generationCount;
	}

	/**
	 * Returning the string representation of each item with “g” if its age is below
	 * maturation, or “G” if above
	 */
	@Override
	public String toString() {
		if (age > this.maturationAge) {
			return Barley.IS_MATURE;
		}
		return Barley.NOT_MATURE;
	}

	/**
	 * Returning the string representation of each item with “b” if its age is below
	 * maturation, or “b” if above
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Item other = (Item) obj;
		return age == other.age && deathAge == other.deathAge && maturationAge == other.maturationAge
				&& monetaryValue == other.monetaryValue;
	}
}
