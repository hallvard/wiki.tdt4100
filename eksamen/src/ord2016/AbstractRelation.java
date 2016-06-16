package ord2016;

import java.util.Collection;

public class AbstractRelation {

	protected final Gender gender;
	
	protected AbstractRelation(Gender gender) {
		this.gender = gender;
	}
	
	protected void addChildren(Person person, Collection<Person> result) {
		if (person != null) {
			for (Person child : person) {
				if ((gender == null || child.getGender() == gender) && (! result.contains(child))) {
					result.add(child);
				}
			}
		}
	}
}
