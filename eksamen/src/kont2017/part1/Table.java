package kont2017.part1;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Manages the set of ordered items for a table (set of guests).
 */
public class Table {

	private Collection<Course> courses = new ArrayList<>();
	private Collection<Meal> meals = new ArrayList<>();

	/**
	 * Add a Course to this bill. 
	 * @param course
	 */
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	/**
	 * Adds a Meal to this bill.
	 * @param meal
	 */
	public void addMeal(Meal meal) {
		this.meals.add(meal);
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
		for (Course course : courses) {
			try {
				total += menu.getPrice(course);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException(e);
			}
		}
		for (Meal meal : meals) {
			try {
				total += menu.getPrice(meal);
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException(e);
			}
		}
		return total;
	}
}
