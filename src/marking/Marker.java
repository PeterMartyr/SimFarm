package marking;

import students.Farm;
import students.Field;
import students.items.Apple;
import students.items.Food;
import students.items.Item;
import students.items.Tilled;
import students.items.Untilled;
import students.items.Weed;

public class Marker {
	
	/* 
	 * Note I ask the player for index of 1 - 10 then 
	 * convert it to 0 - 9 for the array
	 * BTW I only validate user input don't use a 
	 * Zero or a negative number here that poor form
	 */

	private static final int ONE = 1;
	private static final int FIVE = 5;
	private static final int THREE = 3;

	public static void main(String[] args) {
		
		// Section ONE
		
		/*
		 * Note check import 
		 * and
		 * check constants
		 */
		
		//you should NOT be able to make an Item - following line shouldn't compile if uncommented
//		new Item(0, 0, 0);

//		System.out.println(Apple.getGenerationCount()); // should print 0
//		Apple a = new Apple();
//		System.out.println(Apple.getGenerationCount()); // should print 1
//		System.out.println(a); // should print "a"
//		System.out.println(a.getValue()); // should print 0
//		a.setAge(5);
//		System.out.println(a.getValue()); // should print 3
//		System.out.println(a); // should print "A"
//		System.out.println(a.died()); // should print false
//		a.tick();
//		System.out.println(a.died()); // should print true
//		Food testFood = a; //this should be possible
//		Item testItem = testFood; //this should be possible
//
//		System.out.println(new Weed()); // should print "#"
//		System.out.println(new Untilled()); // should print "/"

		// Section TWO
		// uncomment this section once you get to it
		

//		Field testField = new Field(FIVE, FIVE);
//		System.out.println(testField.get(THREE, THREE)); //should print "."
//		System.out.println(testField); //should print the field
//		System.out.println(testField.getValue()); //should print 0
//		a = new Apple();
//		a.setAge(5);
//		testField.plant(ONE, ONE, a);
//		System.out.println(testField.getValue()); //should print 3
//		System.out.println(a.equals(testField.get(ONE, ONE))); //should print true
//		testField.till(ONE, ONE);
//		System.out.println(a.equals(testField.get(ONE, ONE))); //should print false
//		System.out.println(testField.get(ONE, ONE).equals(new Tilled())); //should print true
//		

		// Section THREE
		// uncomment this section once you get to it
		//remember, your farm should work on varies size fields (assuming all fields are less than 10x10)
		Farm f = new Farm(10, 5, 10);
		f.run();
	}
}
