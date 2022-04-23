package students;

/**
 * @author Claudio Pietromartire
 * 
 * @author piece001
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import students.items.Apple;
import students.items.Barley;
import students.items.Food;
import students.items.Wheat;
import students.items.Item;
import students.items.Orange;
import students.items.Tilled;
import students.items.Tractor;

public class Farm {
	private static final char T = 't';
	private static final char H = 'h';
	private static final char P = 'p';
	private static final char Q = 'q';
	private static final char W = 'w'; // lol = Wait and Wheat
	private static final char S = 's';
	private static final char U = 'u'; // UpGrade Menu
	private static final char Y = 'y';
	private static final char N = 'n';
	private static final char B = 'b';
	private static final char O = 'o';
	private static final char A = 'a';
	private static final char M = 'm';
	private static final char F = 'f'; // fuel

	private static final int FIELD_MIN_SIZE = 1;
	private static final char[] COMMANDS_3 = { T, H, P };
	private static final char[] COMMANDS_1 = { S, Q, W, U, M, F };

	private int startingFunds;
	private Field theField;
	private String verbose = "";
	private Scanner sc;
	private char c = 0;
	private int x = 0, y = 0;
	private boolean hasNext;
	private boolean nextExpanse;
	private int hadTurn = 0;
	private Tractor tractor = null;
	private String unlocked = "";
	private boolean nextTractor = false;

	/**
	 * the all args Constructor
	 * 
	 * @param fieldWidth - the width of the field in the farm (X axis)
	 * @param fieldHeight - the height of the field in the farm (Y axis)
	 * @param startingFunds - the money the player starts with used to buy crops
	 */
	public Farm(int fieldWidth, int fieldHeight, int startingFunds) {
		this.sc = new Scanner(System.in);
		this.startingFunds = startingFunds;
		this.theField = new Field(fieldHeight, fieldWidth);
	}

	/**
	 * This is the  engine that needs to run to play the Game
	 * It loops once very time the player has a turn
	 */
	public void run() {
		this.hadTurn++;
		this.displayTheField();
		this.showFunds();
		this.checkUpgrade();
		if (this.tractor != null && hasFuelForRow()) {
			this.promptUserRow();
			this.getInputRow();
			this.workTheRow();

		} else {
			this.promptUserPlot();
			this.getInputPlot();
			this.workThePlot();
		}
		this.run();
	}

	/**
	 * user get sent here when they have no money to plant a Row. but has a tractor
	 */
	private void runPlot() {
		this.displayTheField();
		this.showFunds();
		this.promptUserPlot();
		this.getInputPlot();
		this.workThePlot();
	}

	/**
	 * this is view for the Main Menu for Plot
	 */
	private void promptUserPlot() {
		StringBuilder sb = new StringBuilder("\n\n");
		sb.append(" t 2 6:  till\n");
		sb.append(" h 4 6: harvest\n");
		sb.append(" p 4 7: plant\n");
		sb.append(" s: field summary\n");
		if (this.nextExpanse) {
			sb.append(" u: upgrade farm\n");
			this.unlocked = "Next Field Expansion is Available\n";
		}
		if (this.nextTractor) {
			sb.append(" m: machinery options\n");
			this.unlocked += "Machine Options Available";
		}
		if (this.tractor != null) {
			sb.append(" f: buy fuel\n");
		}
		sb.append(" w: wait\n");
		sb.append(" q: quit\n");
		if (this.tractor != null) {
			sb.append(" Tractor Fuel:");
			sb.append(String.format("% .2f%n",this.tractor.getFuelTank()));
			sb.append("\n");
		}

		this.print(sb.toString());
		this.verboseUser();
		this.showUpgrade();

		if (this.hadTurn < 10) {
			this.print(" Enter a Letter Option\n Then enter a Row Number\n Then Enter a Column Number");
			this.print("\n Or just a Letter for some inputs");
		}
		this.print("\n == Enter your next action ==\n\n");
	}

	/**
	 * this is view for the Main Menu for Rows
	 */
	private void promptUserRow() {
		StringBuilder sb = new StringBuilder("\n\n");
		sb.append(" tractor row options\n");
		sb.append(" t 6:  till\n");
		sb.append(" h 4: harvest\n");
		sb.append(" p 4: plant\n");
		sb.append(" s: field summary\n");
		if (this.nextExpanse) {
			sb.append(" u: upgrade farm\n");
			this.unlocked = "Next Field Expansion is Available\n";
		}
		sb.append(" f: buy fuel\n");
		sb.append(" w: wait\n");
		sb.append(" q: quit\n");
		sb.append(" Tractor Fuel:");
		sb.append(String.format("% .2f%n",this.tractor.getFuelTank()));
		sb.append("\n");
		this.print(sb.toString());
		this.verboseUser();
		this.showUpgrade();

		if (this.hadTurn < 10) {
			this.print(" Enter a Letter Option\n Then enter a Row Number");
			this.print("\n Or just a Letter for some inputs");
		}
		this.print("\n == Enter your next action ==\n\n");
	}

	/**
	 * this calculate whether the player has enough fuel to run the 
	 * tractor for the length of the Row
	 * 
	 * @return - True if they have, false if they don't
	 */
	private boolean hasFuelForRow() {
		// a plot = 0.1 fuel
		return this.tractor.getFuelTank() > (this.theField.getWidthX() / 10);
	}

	/**
	 * with the user input data  for the Row input 
	 * now accepted as valid. It is distributes the data to various
	 * Row methods to acted upon and implemented
	 */
	private void workTheRow() {
		switch (c) {
		case T: // Till the fields
			this.tillRow();
			this.fuelUsedPerRow();
			break;
		case H: // harvest and count profits (if any)
			this.harvestRow();
			this.fuelUsedPerRow();
			break;
		case P: // Plant crops
			if (hasCropFundsRow()) {
				this.plantRow();
				this.fuelUsedPerRow();
			} else {
				this.verbose += " No Funds for planting rows\n";
				this.verbose += " Opening plot options";
				this.runPlot();
			}
			break;
		case U: // Upgrade menu
			if (nextExpanse) {
				this.expandFarm();
			} else {
				this.print("No upgrades available at the moment");
				this.pauseGame();
			}
			break;
		case S: // A Summary Report
			this.printSummary();
			this.pauseGame();
			break;
		case F: // buy Fuel for tractor
			double fuel = this.buyFuel();
			this.tractor.reFuel(fuel);
			if (fuel > 0) {
				this.verbose = "tractor was refueled";
			} else {
				this.verbose = "Tractor was not refueled";
			}
			break;
		case W: // the passage of time, crops grow, weeds grow
			this.tick();
			break;
		case Q: // Exit the Game.
			closeGame();
		}
	}

	/**
	 * calculate how it cost to fill ip the tractor fuel tank
	 * 
	 * @return - the price of filling the fuel tank
	 */
	private double buyFuel() {
		double topHerUp = 0;
		double fillHerUp = Tractor.FUEL_CAPACITY - this.tractor.getFuelTank();
		if (fillHerUp < this.startingFunds) {
			this.startingFunds -= fillHerUp;
			topHerUp = fillHerUp;
		}
		return topHerUp;
	}

	/**
	 * calculate the fuel use for a Row, 
	 * and deducts it from the Fuel Tank
	 */
	private void fuelUsedPerRow() {
		double fuel = Tractor.FUEL_PER_PLOT * this.theField.getWidthX();
		this.tractor.deductFuelUsage(fuel);
	}

	/**
	 * with the user input data  for the plot input 
	 * now accepted as valid. It is distributes the data to various
	 * single Plot methods to acted upon and implemented
	 */
	private void workThePlot() {
		switch (c) {
		case T: // Till the fields
			this.tillPlot();
			break;
		case H: // harvest and count profits (if any)
			this.harvestPlot();
			break;
		case P: // Plant crops
			this.plantPlot();
			break;
		case U: // Upgrade menu
			if (nextExpanse) {
				this.expandFarm();
			} else {
				this.print("No upgrades available at the moment");
				this.pauseGame();
			}
			break;
		case M:
			if (nextTractor && this.tractor == null) {
				this.buyTractor();
			} else {
				this.print("No upgrades available at the moment");
				this.pauseGame();
			}
			break;
		case S: // A Summary Report
			this.printSummary();
			this.pauseGame();
			break;
		case F: // buy Fuel for tractor
			double fuel = this.buyFuel();
			this.tractor.reFuel(fuel);
			if (fuel > 0) {
				this.verbose = "Tractor was refueled";
			} else {
				this.verbose = "Tractor was not refueled";
			}
			break;
		case W: // the passage of time, crops grow, weeds grow
			this.tick();
			break;
		case Q: // Exit the Game.
			closeGame();
			break;
		}
	}

	/**
	 * Calculate whether the player has funds to buy a row of crops
	 * Before they get sent to the Plant Row Menu
	 * 
	 * Note managing the various menu option is very poorly done, the player is required to 
	 * Have fuel to use the tractor or its back to Plot Menu, if try to plant crops 
	 * And don't have money it back to the plot menu, but if they have no money, 
	 * But have fuel and mature crop then they can harvest with the tractor
	 * OGM, it works... in dirty way, I am not happy with.
	 * That all I am going to say, all done without states :(
	 * 
	 * @return - True if they have money to buy crops, else false
	 */


	private boolean hasCropFundsRow() {
		return Orange.VALUE_BUY * this.theField.getWidthX() < this.startingFunds + 10;
	}

	/**
	 * dialogue with to back out of closing the game
	 * R U really really sure??
	 * 
	 * includes commented out development code use to test with.
	 * to use reverse the commenting after default: to suite your needs
	 */
	private void closeGame() {
		this.print("Are you sure?");
		this.print("Enter 'y' or 'n'");
		String input = sc.nextLine();
		if (input.length() > 0) {
			this.c = input.toLowerCase().trim().charAt(0);
		} else {
			this.c = 0;
		}
		switch (this.c) {
		case Y:
			this.print("Closing Down...");
			sc.close();
			System.exit(1);
			break;
		case N:
			break;
		default:
			print(" === Improper Input Try Again ===");
			this.closeGame();
			/*
			 * includes commented out development code use to test with. 
			 * to use reverse the commenting after this to suite your needs
			 */
//			this.print("Easter Eggs and Testing bonuses");
//			// for TESTING LEFT IT IN
//			// TODO ... Cheats
//			// TODO ... Cheats
//			// TODO ... Cheats
//			// TODO ... Cheats
//			// for Expand Farm and get tractor==========================
//			this.startingFunds += 30;
//			this.print("TEST EXPANSION");
////===============================================================================			
//			// to get the  tractor===============================================
//			if (this.tractor == null) {
//				this.tractor = new Tractor();
//			}
//			this.startingFunds += 100;
//			this.nextTractor = false;
//			this.print("TEST THE TRACTOR");
////===============================================================================				
//			// TODO ... Cheats
//			// TODO ... Cheats
//			// TODO ... Cheats
//			// TODO ... Cheats
//			this.pauseGame();
		}
	}

	/**
	 * Pauses the Game
	 */
	private void pauseGame() {
		this.print("\n== Press Enter to Continue ==");
		sc.nextLine();
	}

	/**
	 * Outputs Crops to plant(s) detail (Costs) the user chooses which to plant, the
	 * cost is deducted from the available funds. Avoiding a negative balance was
	 * not implied or required so it is not handled. Coordinate are passed has
	 * parameters
	 * 
	 * @param x a coordinate on the X Angle Factor
	 * @param y a coordinate on the Y Angle Factor
	 */
	private void plantPlot() {

		if (this.theField.get(this.x, this.y) instanceof Tilled) {
			StringBuilder sb = new StringBuilder();
			sb.append("\nEnter:\n");
			sb.append(" - 'a' to buy an Apple Tree for $");
			sb.append(Apple.VALUE_BUY);
			sb.append("\n - 'w' to buy Wheat for $");
			sb.append(Wheat.VALUE_BUY);
			sb.append("\n - 'o' to buy an Orange Tree for $");
			sb.append(Orange.VALUE_BUY);
			sb.append("\n - 'b' to buy Barley for $");
			sb.append(Barley.VALUE_BUY);
			this.print(sb.toString());

			String input;
			Food food = null;
			int price = 0;
			do {
				input = sc.nextLine();
				if (input.length() > 0) {
					this.c = input.toLowerCase().trim().charAt(0);
				} else {
					this.c = 0;
				}
				switch (this.c) {
				case A:
					price = Apple.VALUE_BUY;
					food = new Apple();
					break;
				case W:
					price = Wheat.VALUE_BUY;
					food = new Wheat();
					break;
				case B:
					price = Barley.VALUE_BUY;
					food = new Barley();
					break;
				case O:
					price = Orange.VALUE_BUY;
					food = new Orange();
					break;
				default:
					print(" == Try Again ==");
				}

			} while (food == null);

			if ((startingFunds - price) < 1) {
				this.verbose = "\nFunds not available, you  forfeit a turn";
				this.tick();
			} else {
				this.startingFunds -= price;
				this.theField.plant(this.x, this.y, food);
			}
		} else {
			this.verbose = "\nTilled soil is required to plant food\nWeeds & crops have grown while you dilly dallied";
			this.tick();
		}
	}

	/**
	 * Ask the Player want Crop they like planted in a Row
	 * then plants it, while deducting funds from their Account
	 * checking for Fund happen before this method, so they have
	 * Fund for this transactions 
	 */
	private void plantRow() {

		StringBuilder sb = new StringBuilder();
		sb.append("\nEnter:\n");
		sb.append(" - 'a' for a row of Apple Trees: $");
		sb.append(Apple.VALUE_BUY * this.theField.getWidthX());
		sb.append("\n - 'w' for a row of Wheat: $");
		sb.append(Wheat.VALUE_BUY * this.theField.getWidthX());
		sb.append("\n - 'o' for a row of Orange Tree: $");
		sb.append(Orange.VALUE_BUY * this.theField.getWidthX());
		sb.append("\n - 'b' for a row of Barley: $");
		sb.append(Barley.VALUE_BUY * this.theField.getWidthX());
		this.print(sb.toString());

		String input;
		Food food = null;
		int price = 0;
		int x = this.theField.getWidthX();
		do {
			input = sc.nextLine().toLowerCase().trim();
			if(input.length() > 0) {
				c = input.charAt(0);
				switch (c) {
				case A:
					price = Apple.VALUE_BUY * x;
					food = new Apple();
					break;
				case W:
					price = Wheat.VALUE_BUY * x;
					food = new Wheat();
					break;
				case B:
					price = Barley.VALUE_BUY * x;
					food = new Barley();
					break;
				case O:
					price = Orange.VALUE_BUY * x;
					food = new Orange();
					break;
				default:
					print(" == Try Again ==");
				}
			}
		} while (food == null);

		if (food != null) {
			this.startingFunds -= price;
			this.theField.plantRow(y, food);
		}
	}

	/**
	 * outputs to the screen the the possible outcome of harvesting a row with
	 * financial gain output too
	 */
	private void harvestRow() {
		int value = this.theField.harvestRow(this.y);
		this.startingFunds += value;
		StringBuilder sb = new StringBuilder();
		sb.append("Harvesting the Row return a value of $");
		sb.append(value);
		this.verbose = sb.toString();
	}

	/**
	 * outputs to the screen the the possible outcome of harvesting a plot with
	 * financial gain output too
	 */
	private void harvestPlot() {
		int value = this.theField.harvest(this.x, this.y);
		this.startingFunds += value;
		if (value > 0) {
			this.verbose = new StringBuilder("The crop was successfully harvested\nWith a return of $").append(value)
					.toString();
		} else if (value == 0) {
			this.verbose = new StringBuilder("No income was return $").append(value).toString();
		} else {
			this.verbose = new StringBuilder("Time and money losted negative return $").append(value).toString();
		}
	}

	/**
	 * till the plot of untilled soil removes weeds or existing crops Preparing the
	 * soil for a future crop
	 */
	private void tillRow() {
		this.theField.tillRow(this.y);
		this.verbose = "A Row in the field got tilled ...\n";
	}

	/**
	 * till the plot of untilled soil removes weeds or existing crops Preparing the
	 * soil for a future crop
	 */
	private void tillPlot() {
		this.theField.till(this.x, this.y);
		this.verbose = "A plot in the field got tilled ...\n";
	}

	/**
	 * Last step in the validation process
	 * 
	 * @param x a single coordinate the height of the farm
	 * @param y a single coordinate the width of the farm
	 * @return whether the coordinate is valid to avoid out of bounds exception
	 */
	private boolean checkFieldSize(int x, int y) {
		return ((x >= Farm.FIELD_MIN_SIZE && x <= this.theField.getWidthX())
				&& (y >= Farm.FIELD_MIN_SIZE && y <= this.theField.getHeightY()));
	}

	/**
	 * @param text string to check with Regex to match to numbers
	 * @return true if an number or false if not
	 */
	private boolean isDecimalInteger(String text) {
		Pattern pattern = Pattern.compile("^[\\d#]+$");
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}

	/**
	 * 
	 * @param input the user input char to check
	 * @return true if valid false if not
	 */
	private boolean checkInputCommand(char input) {
		boolean ok = false;
		this.hasNext = false;
		for (char c : Farm.COMMANDS_3) {
			if (c == input) {
				ok = true;
				hasNext = true;
				break;
			}
		}
		for (char c : Farm.COMMANDS_1) {
			if (c == input) {
				ok = true;
			}
		}
		return ok;
	}

	/**
	 * @param text text to check for on whether it is blank or empty
	 * @return true if blank or empty
	 */
	private boolean isBlankOrEmpty(String text) {
		return text.isBlank() || text.isEmpty();
	}

	/**
	 * It takes in all the USER INPUT Runs the various Validating Process User get
	 * feedback for incorrect input once the data is accepted it move to the next
	 * stage
	 */
	private void getInputRow() {
		String input;
		String[] commands;
		boolean ok = false;
		input = sc.nextLine().trim().toLowerCase();
		if (!this.isBlankOrEmpty(input)) {
			commands = input.split("\\s");
			c = commands[0].charAt(0);
			if (this.checkInputCommand(c)) {
				if (this.hasNext && commands.length == 2) {
					if (this.isBlankOrEmpty(commands[1])) {
						this.verbose = "UNKOWN ERROR THIS SHOULD NOT HAPPEN:(";
					}
					if (this.isDecimalInteger(commands[1])) {
						this.y = Integer.parseInt(commands[1]);
					} else {
						this.verbose = "The row number was NOT a Number!";
					}
					if (this.y > 0 && this.y <= this.theField.getHeightY()) {
						// handle the index from the user to match the array
						this.y--;
						ok = true;
					} else {
						this.verbose = "HUH, the row does not exist\n";
					}
				} else if (!this.hasNext) {
					ok = true;
				} else {
					this.verbose = "watch what you input!\nJust Sayin'\n";
				}
			} else {
				this.verbose = new StringBuilder(
						"Invalid command Character! Only 't, h, p' or \n's, w, p with coordinates'\n")
								.append("seperated by 1 whitespace are acceptable\n").toString();
			}
		} else {
			this.verbose = "Whitespace is not valid command Letter\n";
		}
		if (!ok) {
			this.run();
		}
	}

	/**
	 * It takes in all the USER INPUT Runs the various Validating Process User get
	 * feedback for incorrect input once the data is accepted it move to the next
	 * stage
	 */
	private void getInputPlot() {
		String input;
		String[] commands;
		boolean ok = false;
		input = sc.nextLine().trim().toLowerCase();
		if (!this.isBlankOrEmpty(input)) {
			commands = input.split("\\s");
			this.c = commands[0].charAt(0);
			if (this.checkInputCommand(c)) {
				if (this.hasNext && commands.length == 3) {
					if (this.isBlankOrEmpty(commands[1]) || this.isBlankOrEmpty(commands[2])) {
						this.verbose = "UNKOWN ERROR THIS SHOULD NOT HAPPEN:(";
					}
					if (this.isDecimalInteger(commands[1])) {
						this.y = Integer.parseInt(commands[1]);
					} else {
						this.verbose = "Doh, Enter a correct Row!";
					}
					if (this.isDecimalInteger(commands[2])) {
						this.x = Integer.parseInt(commands[2]);
					} else {
						this.verbose = "Doh, Enter a correct Column!";
					}
					if (this.checkFieldSize(x, y)) {
						// handle the index from the user to match the array
						this.y--;
						this.x--;
						ok = true;
					} else {
						this.verbose = new StringBuilder(
								"Invalid Field coordinates!!!!\nOPTIONS: one char 'c' is a Command Only \n").append(
										"Command and coordinates OPTIONS:\n'c 1 1' || 'c 1 10' || 'c 10 1' || 'c 10 10'")
										.append("\nwhitespace is used as a delimmiter\n").toString();
					}
				} else if (!this.hasNext) {
					ok = true;
				} else {
					this.verbose = "Check your both coorderinates!\nJust Sayin'\n";
				}
			} else {
				this.verbose = new StringBuilder(
						"Invalid command Character! Only 't, h, p' or \n's, w, p with coordinates'\n")
								.append("seperated by 1 whitespace are acceptable\n").toString();
			}
		} else {
			this.verbose = "Whitespace is not valid command Letter\n";
		}
		if (!ok) {
			this.run();
		}
	}

	/**
	 * display the Field with it's crops
	 */
	private void displayTheField() {
		this.print("");
		this.print(this.theField.toString());
	}

	/**
	 * calls the tick method in the work The Field/Row Methods
	 * create to make work The Field/Row Methods more human readable
	 */
	private void tick() {
		this.theField.tick();
	}

	/**
	 * calls the printSummary method in the work The Field/Row Methods
	 *  create to make work The Field/Row Methods more human readable
	 */
	private void printSummary() {
		this.print(this.theField.getSummary());
	}

	/**
	 * show text when Upgrade option are available
	 * then clears the text
	 */
	private void showUpgrade() {
		if (this.unlocked != "") {
			print(this.unlocked);
			this.unlocked = "";
		}
	}

	/**
	 * this output the Funds note: in my game can be negative
	 * If you harvest weed
	 */
	private void showFunds() {
		StringBuilder sb = new StringBuilder("\n\n Bank balance: $").append(this.startingFunds);
		this.print(sb.toString());
	}

	/**
	 * Outputs variable text data to the user
	 * then clears the text, ready for the next output.
	 */
	private void verboseUser() {
		if (!(this.verbose.isEmpty())) {
			this.print(verbose);
			this.verbose = "";
		}
	}

	/**
	 * short cut to avoid writing
	 * System.out.println();
	 * 
	 * @param s - text to print
	 */
	private void print(String s) {
		System.out.println(s);
	}

	/**
	 * check the player funds for extra's option's like
	 * Expand Farm
	 * buy tractor
	 */
	private void checkUpgrade() {

		if (this.hasFundsForLand()) {
			this.nextExpanse = true;
		} else if (this.nextExpanse) {
			this.nextExpanse = false;
		}
		if (this.tractor == null && this.startingFunds > Tractor.BUY_COST + 30) {
			this.nextTractor = true;
		}
	}
	
	/**
	 * dialogue option for input on expanding the the farm
	 * Yes or No?
	 */
	private void expandFarm() {
		this.print("Do you wish to expand your farm for $".concat(String.valueOf(this.calcExpandCost())));
		this.print("Enter 'y' or 'n'");
		String input = sc.nextLine();
		if (input.length() > 0) {
			this.c = input.toLowerCase().trim().charAt(0);
		} else {
			this.c = 0;
		}
		switch (c) {
		case Y:
			this.upGradeFarm();
			break;
		case N:
			break;
		default:
			print("Just 'y' or 'n'");
			expandFarm();
		}
	}

	/**
	 * increases the size of the Farm on the X and Y Factor by 1
	 */
	private void upGradeFarm() {
		
		int xOld = theField.getWidthX();
		int yOld = theField.getHeightY();
		int xNew = xOld + 1;
		int yNew =  yOld + 1;
		Item[][] newField = new Item[yNew][xNew];
		for (int y = 0; y < newField.length; y++) {
			for (int x = 0; x < newField[y].length; x++) {
				if (y < yOld && x < xOld) {
					newField[y][x] = theField.get(x, y);
				} else {
					newField[y][x] = new Tilled();
				}
			}
		}
		this.theField.setField(newField);
		this.verbose = "Upgrades Complete";
	}
	/***
	 * check if the player has funds for a Land Expansion
	 * 
	 * @return - True if they have, else false
	 */
	private boolean hasFundsForLand() {
		int twenty = 20;
		return this.startingFunds - this.calcExpandCost() >= twenty;
	}

	/**
	 * 
	 * @return - the cost the field Expansion according the land added
	 */
	private int calcExpandCost() {

		int x = theField.getWidthX();
		int y = theField.getHeightY();
		return (calcArea(x + 1, y + 1) - calcArea(x, y));
	}

	/**
	 * While writing this I thought 
	 * 
	 * "Bet there a Math.calcArea Function I could have used"
	 * 
	 * @param x  - x axis
	 * @param y  - y axis
	 * @return  - the area of the rectangle or square
	 */
	private int calcArea(int x, int y) {
		return x * y;
	}

	/**
	 * dialogue option for input on buying a tractor
	 * Yes or No?
	 */
	private void buyTractor() {
		this.print("Do you wish to buy a Tractor for $".concat(String.valueOf(Tractor.BUY_COST)));
		this.print("Enter 'y' or 'n'");
		String input = sc.nextLine();
		if (input.length() > 0) {
			this.c = input.toLowerCase().trim().charAt(0);
		} else {
			this.c = 0;
		}
		switch (this.c) {
		case Y:
			this.tractor = new Tractor();
			this.nextTractor = false;
			break;
		case N:
			break;
		default:
			print("Just 'y' or 'n'");
			this.buyTractor();
		}
	}
}
