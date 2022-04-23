package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public class Wheat extends Grain{
	
	private static final int AGE_HARVEST = 2;
	private static final int AGE_DEATH = 6;
	private static final int VALUE_HARVEST = 2; 
	public static final int VALUE_BUY = 1;

	private static final String NOT_MATURE = "w";
	private static final String IS_MATURE = "W";
	
	private static int generationCount = 0;

	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - when the item is ready to Harvest
	 * @param deathAge      - when the item dies
	 * @param monetaryValue - is the return price for successfully harvesting the crop
	 */
	public Wheat(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
		Wheat.generationCount++;
	}
	
	/**
	 * the No Args Constructor with default values
	 */
	public Wheat() {
		this(Wheat.AGE_HARVEST, Wheat.AGE_DEATH, Wheat.VALUE_HARVEST);
	}
	
	/**
	 * Copy constructor
	 * @param other object of this class
	 */
	private Wheat(Wheat other) {
		super(other.maturationAge, other.deathAge, other.monetaryValue);
		this.age = other.age;
	}
	
	/**
	 * Returns a clone of this object
	 */
	@Override
	public Item clone() {
		return new Wheat(this);
	}
	
	/**
	 * 
	 * @return Return a static count of all object created
	 */
	public static int getGenerationCount() {
		return Wheat.generationCount;
	}
	
	/**
	 * Returning the string representation of each item 
	 * with “g” if its age is below maturation, 
	 * or “G” if above
	 */
	@Override
	public String toString() {
		if (age > this.maturationAge) {
			return Wheat.IS_MATURE;
		}
		return Wheat.NOT_MATURE;
	}
	
	/**
	 * indicates whether some other object is "equal to" this one.
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
