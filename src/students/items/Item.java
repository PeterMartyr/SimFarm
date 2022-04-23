package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
import java.util.Objects;

public abstract class Item {
	
	protected int age = 0;
	protected int maturationAge;
	protected int deathAge;
	protected int monetaryValue;
	
	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - age when it is ready to Harvest
	 * @param deathAge      - dies greate than this age
	 * @param monetaryValue - return price for successfully harvesting the crop
	 */
	public Item(int maturationAge, int deathAge, int monetaryValue) {
		super();
		this.maturationAge = maturationAge;
		this.deathAge = deathAge;
		this.monetaryValue = monetaryValue;
	}
	
	/**
	 * create a clone of the object
	 */
	public abstract Item clone();
	
	/**
	 * sets the age of an item
	 * @param i the amount to set the age by
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Increment age by one
	 */
	public void tick() {
		this.age++;
	}
	
	/**
	 * 
	 * @return true if the age is greater than death age
	 */
	public boolean died() {
		return this.age > this.deathAge;
	}
	
	/**
	 *
	 * @return an int amount for monetary value of the item, if mature else 0
	 */
	public int getValue() {
		int value = 0;
		if (age > this.maturationAge){
			value = monetaryValue; 
		}
		return value;
	}
	
	/**
	 * Returns a string representation of the object
	 */
	public abstract String toString();

	@Override
	public int hashCode() {
		return Objects.hash(age, deathAge, maturationAge, monetaryValue);
	}
	/**
	 * Returns a hash code value for the object.
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
