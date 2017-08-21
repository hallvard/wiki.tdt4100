package kont2017.part2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a set of Courses that are ordered as a whole
 */
public class Meal extends MenuItem implements Iterable<Course> {

	private Collection<Course> courses;

	/**
	 * Initializes the Meal with the provided name, description and Courses.
	 * @param name
	 * @param description
	 * @param courses
	 */
	// vis med et eksempel hvordan denne konstruktøren kalles
	public Meal(String name, String description, Course[] courses) {
		super(name, description);
		this.courses = Arrays.asList(courses);
	}
	
	//

	@Override
	public Iterator<Course> iterator() {
		return courses.iterator();
	}
	
	//
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
}
