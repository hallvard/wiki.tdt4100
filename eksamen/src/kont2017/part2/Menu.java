package kont2017.part2;

import java.util.Map;

/**
 * Manages the set of Courses and Meals offered and their prices.
 */
public class Menu {

	private Map<MenuItem, Double> items;

	/**
	 * Gets the price for a Course.
	 * @param course
	 * @return
	 * @throws IllegalArgumentException if this Menu doesn't include the provided Course
	 */
	public double getPrice(Course course) throws IllegalArgumentException {
		if (! items.containsKey(course)) {
			throw new IllegalArgumentException("This menu does not include the course " + course.getName());
		}
		return items.get(course);
	}

	/**
	 * Sets/changes the price of the provided MenuItem.
	 * @param item
	 * @param price
	 */
	public void updatePrice(MenuItem item, double price) {
		items.put(item, price);
	}
	
	/**
	 * Gets the price for a Meal. If the registered price is 0.0,
	 * the price is computed as the sum of the prices of the Meal's courses.
	 * @param meal
	 * @return
	 * @throws IllegalArgumentException if this Menu doesn't include the provided Meal,
	 *  or if a price of a Course is needed, but is missing
	 */
	public double getPrice(Meal meal) throws IllegalArgumentException {
		if (! items.containsKey(meal)) {
			throw new IllegalArgumentException("This menu does not include the meal " + meal.getName());
		}
		double total = items.get(meal);
		if (total == 0.0) {
			for (Course course : meal) {
				total += getPrice(course);
			}
		}
		return total;
	}
}
