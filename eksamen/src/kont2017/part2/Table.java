package kont2017.part2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Manages the set of ordered items for a table (set of guests).
 */
public class Table {

	private Collection<MenuItem> items = new ArrayList<>();

	/**
	 * Add a MenuItem to this bill. 
	 * @param item
	 */
	public void addItem(MenuItem item) {
		this.items.add(item);
	}

	//
	
	private final Menu menu;
	
	/**
	 * Initializes a new Bill with a Menu that provides the prices for the Courses and Meals
	 * @param menu
	 */
	public Table(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Computes the total price for all the added items. Prices are provided by the Menu.
	 * @return the total price
	 * @throws IllegalStateException when the price of an item cannot be provided by the Menu
	 */
	public double getPrice() throws IllegalStateException {
		double total = 0.0;
		for (MenuItem item : items) {
			try {
				// note: cannot use menu.getPrice(item), since there is no menu.getPrice(MenuItem) method
				if (item instanceof Course) {
					total += menu.getPrice((Course) item);
				} else if (item instanceof Meal) {
					total += menu.getPrice((Meal) item);
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException(e);
			}
		}
		return total;
	}
}
