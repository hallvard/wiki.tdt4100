package kont2017.part1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a set of Courses that are ordered as a whole
 */
public class Meal implements Iterable<Course> {

	private final String name, description;

	private Collection<Course> courses;

	/**
	 * Initializes the Meal with the provided name, description and Courses.
	 * @param name
	 * @param description
	 * @param courses
	 */
	// vis med et eksempel hvordan denne konstruktøren kalles
	public Meal(String name, String description, Course[] courses) {
		super();
		this.name = name;
		this.description = description;
		// hva ville skjedd om en utelot this, her:
		// - feilmelding ved kompilering (rød strek i Eclipse)
		// - varsel om mulig feil (gul strek i Eclipse)
		// - kræsj ved kjøring
		// - logisk feil ved kjøring
		// - det virker som det skal
		this.courses
		// skriv en (mulig) signatur til asList-metoden,
		// som kalles i linja under 
		= Arrays.asList(courses);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
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
