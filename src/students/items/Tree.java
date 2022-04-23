package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public abstract class Tree extends Food {

	protected boolean harvested = false;
	
	/**
	 * All Args constructor
	 * 
	 * @param maturationAge - age when it is ready to Harvest
	 * @param deathAge      - dies greater than this age
	 * @param monetaryValue - return price for successfully harvesting the crop
	 */
	public Tree(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}

	/**
	 * allows Fruit Trees to only be harvested once a tick
	 * once it is mature
	 */
	public boolean isHarvested() {
		return harvested;
	}

	/**
	 * @param harvested - true if just been harvested 
	 * 				false if resetting for the next tick
	 */
	public void setHarvested(boolean harvested) {
		this.harvested = harvested;
	}
}