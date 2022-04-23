package students.items;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
public class Orange extends Tree {
		
		private static final int AGE_HARVEST = 5; 
		private static final int AGE_DEATH = 8;
		private static final int VALUE_HARVEST = 6; 
		public static final int VALUE_BUY = 3;

		private static final String NOT_MATURE = "o";
		public static final String IS_MATURE = "O";
		
		private static int generationCount = 0;
		
		/**
		 * All Args constructor
		 * 
		 * @param maturationAge - when the item is ready to Harvest
		 * @param deathAge      - when the item dies
		 * @param monetaryValue - is the return price for successfully harvesting the crop
		 */
		public Orange(int maturationAge, int deathAge, int monetaryValue) {
			super(maturationAge, deathAge, monetaryValue);
			Orange.generationCount++;
		}
		
		/**
		 * the No Args Constructor with default values
		 */
		public Orange() {
			this(Orange.AGE_HARVEST, Orange.AGE_DEATH, Orange.VALUE_HARVEST);
		}
		
		/**
		 * Copy constructor
		 * @param other object of this class
		 */
		private Orange(Orange other) {
			super(other.maturationAge, other.deathAge, other.monetaryValue);
			this.age = other.age;
			this.harvested = ((Tree)other).harvested;
		}
		
		/**
		 * a clone of this object
		 */
		@Override
		public Orange clone() {
			return new Orange(this);
		}
		
		/**
		* 
		* @return Return a static count of all object created
		*/
		public static int getGenerationCount() {
			return Orange.generationCount;
		}
		
		/**
		 * Returning the string representation of each item 
		 * with “g” if its age is below maturation, 
		 * or “G” if above
		 */
		@Override
		public String toString() {
			if (age > this.maturationAge) {
				return Orange.IS_MATURE;
			}
			return Orange.NOT_MATURE;
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
