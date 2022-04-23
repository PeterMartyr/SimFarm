package students;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
import students.items.Apple;
import students.items.Barley;
import students.items.Food;
import students.items.Tree;
import students.items.Grain;
import students.items.Wheat;
import students.items.Item;
import students.items.Orange;
import students.items.Tilled;
import students.items.Untilled;
import students.items.Weed;

public class Field {

	private static final double CHANCE_OF_WEED_25 = 0.25;
	private static final double CHANCE_OF_WEED_10 = 0.1;

	private static final String SUMMARY_START = "========= Summary ==========\n";
	private static final String SUMMARY_END = "=============================";
	private static final String TOTAL_WHEAT_CREATED = "Total grain created:";
	private static final String TOTAL_BARLEY_CREATED = "Total barley created:";
	private static final String TOTAL_APPLES_CREATED = "Total apples created:";
	private static final String TOTAL_ORANGES_CREATED = "Total oranges created:";
	private static final String FOR_A_TOTAL_OF = "For a total of:";
	private static final String WEED = "Weed:";
	private static final String UNTILLED = "Untilled Soil:";
	private static final String TILLED = "Tilled Soil:";
	private static final String WHEAT = "Wheat:";
	private static final String APPLE = "Apple Tree:";
	private static final String BARLEY = "Barley:";
	private static final String ORANGE = "Orange Tree:";

	private Item[][] field;

	/**
	 * the all args constructor
	 * 
	 * @param height the size to make the y axis of the field
	 * @param width  the size to make the x axis of the field
	 */
	public Field(int height, int width) {
		super();
		this.field = new Item[height][width];
		this.setUp();
	}

	/**
	 * this give the condition of the farm in in a string report including crop
	 * information
	 * 
	 * @return A formatted string report
	 */
	public String getSummary() {

		String report = SUMMARY_START;
		int[] num = countFieldItems();
		String[][] textArray = createReport(num);
		report = formatReport(report, textArray);
		report += SUMMARY_END;
		return report;
	}

	/**
	 * Return a report formatted into columns
	 * 
	 * @param z the Report that been prepare for formatting into
	 * @param s the i-dimensional array ready to be formatted into columns
	 * @return a formatting string in columns===
	 */
	private String formatReport(String text, String[][] textArray) {
		for (int x = 0; x < textArray.length; x++) {
			for (int y = 0; y < 1; y++) {
				text += String.format("%-22s  %-1s", textArray[x][y], textArray[x][y + 1]);
			}
		}
		return text;
	}

	/**
	 * It start the initial formatting for report for numerical values of distinct
	 * items and others
	 * 
	 * @param num a array of distinct item counted that are planted in the field
	 * 
	 * @return bi-dimensional array ready to be formatted into columns
	 */
	private String[][] createReport(int[] num) {
		String[][] s = new String[12][2];
		s[0][0] = Field.WHEAT;
		s[1][0] = Field.BARLEY;
		s[2][0] = Field.APPLE;
		s[3][0] = Field.ORANGE;
		s[4][0] = Field.TILLED;
		s[5][0] = Field.UNTILLED;
		s[6][0] = Field.WEED;
		s[7][0] = Field.FOR_A_TOTAL_OF;
		s[8][0] = Field.TOTAL_WHEAT_CREATED;
		s[9][0] = Field.TOTAL_BARLEY_CREATED;
		s[10][0] = Field.TOTAL_APPLES_CREATED;
		s[11][0] = Field.TOTAL_ORANGES_CREATED;

		s[0][1] = String.format("%4d%n", num[0]);
		s[1][1] = String.format("%4d%n", num[1]);
		s[2][1] = String.format("%4d%n", num[2]);
		s[3][1] = String.format("%4d%n", num[3]);
		s[4][1] = String.format("%4d%n", num[4]);
		s[5][1] = String.format("%4d%n", num[5]);
		s[6][1] = String.format("%4d%n", num[6]);
		s[7][1] = "$" + String.format("%3d%n", this.getValue());
		s[8][1] = String.format("%4d%n", Wheat.getGenerationCount());
		s[9][1] = String.format("%4d%n", Barley.getGenerationCount());
		s[10][1] = String.format("%4d%n", Apple.getGenerationCount());
		s[11][1] = String.format("%4d%n", Orange.getGenerationCount());
		return s;
	}

	/**
	 * 
	 * @return a Array with distinct count of the types Wheat Tilled Untilled Weed
	 *         for summary report
	 * 
	 */
	private int[] countFieldItems() {
		int[] num = new int[7];
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				var v = field[y][x].clone();
				if (v instanceof Wheat) {
					num[0]++;
				} else if (v instanceof Barley) {
					num[1]++;
				} else if (v instanceof Apple) {
					num[2]++;
				} else if (v instanceof Orange) {
					num[3]++;
				} else if (v instanceof Tilled) {
					num[4]++;
				} else if (v instanceof Untilled) {
					num[5]++;
				} else if (v instanceof Weed) {
					num[6]++;
				}
			}
		}
		return num;
	}

	/**
	 * Create the object in the field when this object is created
	 */
	// package ?? if ya say so
	void setUp() {
		int height = this.field.length;
		int width = this.field[0].length;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				this.field[y][x] = new Tilled();
			}
		}
	}

	/**
	 * Returns representation of the item in a String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("    ");
		int height = this.field.length; // Y Angle
		int width = this.field[0].length; // X Angle

		for (int i = 1; i < width + 1; i++) {
			if (i < 10) {
				sb.append(String.valueOf(i));
				sb.append(" ");
			} else {
				sb.append(String.valueOf(i));
			}
		}
		sb.append("\n");
		for (int y = 0; y < height; y++) {
			if (y < 9) {
				sb.append(" ");
				sb.append(String.valueOf(y + 1));
				sb.append("  ");
			} else {
				sb.append(" ");
				sb.append(String.valueOf(y + 1));
				sb.append(" ");
			}
			for (int x = 0; x < width; x++) {
				sb.append(field[y][x]);
				sb.append(" ");
			}
			if (y < height - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return the value of all the object in the field
	 */
	public int getValue() {
		int i = 0;
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				i += field[y][x].clone().getValue();
			}
		}
		return i;
	}

	/**
	 * plant an object to grow at this spot
	 * 
	 * @param x    is the column coordinate
	 * @param y    is the row coordinate
	 * @param food is the type to plant at x & y
	 */
	public void plant(int x, int y, Food food) {
		field[y][x] = food;
	}

	/**
	 * Tils the soil at the given to prepare it for planting
	 * 
	 * @param x is the column coordinate
	 * @param y is the row coordinate
	 */
	public void till(int x, int y) {
		field[y][x] = new Tilled();
	}

	/**
	 * Every Food Item in the field must have its tick() function called to increase
	 * the age of each item.If an item in the field has died after ageing, it turns
	 * into Untilled. If an Item is Tilled, 20% of the time that location will turn
	 * into a new Weed.
	 */
	public void tick() {

		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				Item item = field[y][x];
				if (item instanceof Food) {
					item.tick();
					if (item.died()) {
						field[y][x] = new Untilled();
					} else if (item instanceof Tree) {
						((Tree) item).setHarvested(false);
					}
				} else if (item instanceof Tilled && Math.random() < CHANCE_OF_WEED_25) {
					field[y][x] = new Weed();
				} else if (item instanceof Untilled && Math.random() < CHANCE_OF_WEED_10) {
					field[y][x] = new Weed();
				}
			}
		}
	}

	/**
	 * 
	 * @return the width of the field
	 */
	public int getWidthX() {
		return this.field[0].length;
	}

	/**
	 * 
	 * @return the height of the field
	 */
	public int getHeightY() {
		return this.field.length;
	}

	/**
	 * 
	 * @param x is the column coordinate
	 * @param y is the row coordinate
	 * @return the Item at the given spot
	 */
	public Item get(int x, int y) {
		return this.field[y][x].clone();
	}

	/**
	 * 
	 * @param x is the column coordinate
	 * @param y is the row coordinate
	 * @return The value of the item at the given spot
	 * 
	 *         cuts Grain while harvesting, replacing with soil
	 */
	public int harvest(int x, int y) {

		Item item = this.field[y][x].clone();
		int value = item.getValue();
		if (value > 0) {
			if (item instanceof Grain) {
				field[y][x] = new Untilled();
			} else if (!((Tree) item).isHarvested()) {
				// damn that clone :( I got old habits!!
				((Tree) this.field[y][x]).setHarvested(true);
			} else {
				value = 0;
			}
		}
		return value;
	}

	/**
	 * Used during farm expansion, replace the Field with a bigger one
	 * 
	 * @param field this is the bigger replacement field
	 */
	public void setField(Item[][] field) {
		this.field = field;
	}

	/**
	 * 
	 * @param y    >this entire row get plant with
	 * @param food > this food type
	 */
	public void plantRow(int y, Food food) {
		for (int i = 0; i < field[y].length; i++) {
			field[y][i] = food.clone();
		}
	}

	/**
	 * 
	 * @param y till the row at this number y replacing the item with tilled soil
	 */
	public void tillRow(int y) {
		for (int i = 0; i < field[y].length; i++) {
			field[y][i] = new Tilled();
		}
	}

	/**
	 * 
	 * @param y Harvest the food at this row y
	 * @return the the value of the entire row
	 */
	public int harvestRow(int y) {
		int value = 0;
		int temp;
		for (int i = 0; i < field[y].length; i++) {
			temp = this.field[y][i].getValue();
			if (temp > 0) {
				if (this.field[y][i] instanceof Grain) {
					this.field[y][i] = new Untilled();
				} else if (!((Tree) this.field[y][i]).isHarvested()) {
					((Tree) this.field[y][i]).setHarvested(true);
				} else {
					temp = 0;
				}
			}
			value += temp;
		}
		return value;
	}
}
