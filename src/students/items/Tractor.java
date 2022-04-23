package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public class Tractor extends Item {

	private static final String TO_STRING = "Farm machinery to work the Farm";
	private static final int AGE_HARVEST = Integer.MIN_VALUE;
	private static final int AGE_DEATH = Integer.MAX_VALUE;
	private static final int VALUE_HARVEST = 0;
	public static final double FUEL_PER_PLOT = 0.1;
	public static final double FUEL_CAPACITY = 10;
	public static final double FUEL_COST = 1; // straight trade 1 = fuel for 10 plots
	public static final int BUY_COST = 100;

	private double fuelTank;

	/**
	 * the No Args Constructor with default values
	 */
	public Tractor() {
		super(AGE_HARVEST, AGE_DEATH, VALUE_HARVEST);
		this.fuelTank = FUEL_CAPACITY;
	}

	/**
	 * Copy constructor
	 * 
	 * @param other object of this class
	 */
	private Tractor(Tractor other) {
		super(AGE_HARVEST, AGE_DEATH, VALUE_HARVEST);
		this.fuelTank = other.fuelTank;
	}

	/**
	 * creates a clone of this object
	 */
	@Override
	public Item clone() {
		return new Tractor(this);
	}

	/**
	 * get the current fuel in the tank
	 */
	public double getFuelTank() {
		return new Tractor(this).fuelTank;
	}

	/**
	 * set the size of the fuel tank
	 */
	public void setFuelTank(double fuelTank) {
		this.fuelTank = fuelTank;
	}

	/**
	 * representation of this object in a String
	 */
	@Override
	public String toString() {
		return TO_STRING;
	}

	/**
	 * 
	 * @param fuel how fuel this use while working
	 */
	public void deductFuelUsage(double fuel) {
		this.fuelTank -= fuel;
	}

	/**
	 * 
	 * @param buyFuel the amount to add/fill the fuel tank
	 */
	public void reFuel(double buyFuel) {
		this.fuelTank += buyFuel;
	}
}
